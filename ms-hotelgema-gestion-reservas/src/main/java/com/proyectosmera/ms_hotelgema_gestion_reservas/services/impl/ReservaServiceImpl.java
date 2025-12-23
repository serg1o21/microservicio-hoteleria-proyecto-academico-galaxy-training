package com.proyectosmera.ms_hotelgema_gestion_reservas.services.impl;

import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.cache.HabitacionCache;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.client.UsuarioClient;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.habitacion.HabitacionRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.habitacion.HabitacionResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.DetalleReservaResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.kafka.dto.ReservaNotificacionEventDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.ReservaRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.ReservaResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.client.HabitacionClient;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.usuarios.UsuarioResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.kafka.producer.ReservaNotificacionProducer;
import com.proyectosmera.ms_hotelgema_gestion_reservas.mappers.DetalleReservaMapper;
import com.proyectosmera.ms_hotelgema_gestion_reservas.mappers.ReservaMapper;
import com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.entities.DetalleReservaEntity;
import com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.entities.ReservaEntity;
import com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.repositories.DetalleReservaRepository;
import com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.repositories.ReservaRepository;
import com.proyectosmera.ms_hotelgema_gestion_reservas.services.ReservaService;
import com.proyectosmera.ms_hotelgema_gestion_reservas.utils.CodigoReservaUtil;
import jakarta.validation.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {
  private final HabitacionClient habitacionClient;
  private final UsuarioClient usuarioClient;
  private final ReservaRepository reservaRepository;
  private final DetalleReservaRepository detalleReservaRepository;
  private final ReservaMapper reservaMapper;
  private final DetalleReservaMapper detalleReservaMapper;
  private final ReservaNotificacionProducer reservaNotificacionProducer;
  private final HabitacionCache habitacionCache;
  @Override
  public List<HabitacionResponseDto> listarHabitacionesDisponibles(
      LocalDate checkIn,
      LocalDate checkOut
  ) {
    var habitacionesOcupadas = detalleReservaRepository.findHabitacionesOcupadas(checkIn, checkOut);
    List<HabitacionResponseDto> habitaciones;
    try {
      habitaciones = habitacionClient.listarHabitaciones();
      habitacionCache.update(habitaciones);
    } catch (Exception ex) {
      log.warn("ms-habitaciones no disponible, usando cache");
      habitaciones = habitacionCache.get();
    }

    if (!habitacionCache.hasData()) {
      throw new ValidationException(
          "No se pudo validar disponibilidad de habitaciones"
      );
    }

    return habitaciones.stream()
        .filter(h -> !habitacionesOcupadas.contains(h.getNumeroHabitacion()))
        .toList();
  }

  @Override
  public List<ReservaResponseDto> listarReservas() {
    return reservaMapper.toReservaResponseDtoList(reservaRepository.findAll());
  }

  @Override
  public List<DetalleReservaResponseDto> listarDetallesPorReserva(Integer idReserva) {
    return detalleReservaMapper.toDetalleReservaResponseDtoList(detalleReservaRepository.findByReservaEntity_Id(idReserva));
  }

  @Override
  public ReservaResponseDto crearReserva(ReservaRequestDto reservaRequestDto) {

    UsuarioResponseDto usuarioEncontrado =
        usuarioClient.buscarUsuarioPorDni(reservaRequestDto.getDni())
            .orElseThrow(() -> new ValidationException("No se encontró usuario con ese DNI"));

    double precioTotal = 0.0;
    var habitacionesDisponibles = listarHabitacionesDisponibles(
        reservaRequestDto.getCheckIn(),
        reservaRequestDto.getCheckOut()
    );
    log.info("habitacionesDisponibles: " + habitacionesDisponibles);

    var habitacionesRequest = reservaRequestDto.getHabitaciones();
    log.info("habitaciones request: " + habitacionesRequest);
    List<HabitacionResponseDto> habitacionesSeleccionadas = listarHabitacionesSeleccionadas(habitacionesRequest, habitacionesDisponibles);

    log.info("habtaciones seleccionadas: " + habitacionesSeleccionadas);

    ReservaEntity nuevaReserva = reservaMapper.toEntity(reservaRequestDto);
    //seteamos lo del sistema
    nuevaReserva.setCodigoReserva(generarCodigoReserva());
    nuevaReserva.setFechaReserva(LocalDateTime.now());
    nuevaReserva.setEstado("CONFIRMADA");
    //creamos la reserva
    ReservaEntity reservaGuardada = reservaRepository.save(nuevaReserva);
    //generamos el detalle
    for(HabitacionResponseDto hab : habitacionesSeleccionadas) {
      DetalleReservaEntity detalle = new DetalleReservaEntity();
      detalle.setReservaEntity(reservaGuardada);
      detalle.setNumHabitacion(hab.getNumeroHabitacion());
      detalle.setPrecio(hab.getPrecioNoche());
      detalle.setDias((int) ChronoUnit.DAYS.between(reservaGuardada.getCheckIn(), reservaGuardada.getCheckOut()));
      detalle.setSubtotal(hab.getPrecioNoche()*detalle.getDias());
      detalleReservaRepository.save(detalle);
      //el acumulador del total
      precioTotal = precioTotal + detalle.getSubtotal();
    }
    //actualizamos la reserva
    reservaGuardada.setTotal(precioTotal);
    ReservaEntity reservaActualizada = reservaRepository.save(reservaGuardada);
    //enviamos el evento
    enviarEventoReservaNotificacion(reservaActualizada, usuarioEncontrado);

  return reservaMapper.toResponseDto(reservaActualizada);
  }

  @Override
  public ReservaResponseDto actualizarReserva(Integer id, ReservaRequestDto reservaRequestDto) {
    return null;
  }


  private List<HabitacionResponseDto> listarHabitacionesSeleccionadas(List<HabitacionRequestDto> habitacionesRequest, List<HabitacionResponseDto> habitacionesDisponibles){
    boolean algunaNoDisponible = habitacionesRequest.stream()
        .map(HabitacionRequestDto::getNumHabitacion)
        .anyMatch(numHabitacionSolicitado ->
            habitacionesDisponibles.stream()
                .noneMatch(hd -> hd.getNumeroHabitacion().equals(numHabitacionSolicitado))
        );

    if (algunaNoDisponible) {
      throw new ValidationException("Se encontró habitaciones ocupadas");
    }

    return habitacionesDisponibles.stream()
        .filter(hd ->
            habitacionesRequest.stream()
                .anyMatch(hr -> hr.getNumHabitacion().equals(hd.getNumeroHabitacion()))
        )
        .toList();
  }


  private void enviarEventoReservaNotificacion(ReservaEntity reserva, UsuarioResponseDto usuario){
    //buscamos el detalle de la reserva
    List<DetalleReservaResponseDto> detalleReserva = listarDetallesPorReserva(reserva.getId());
    ReservaNotificacionEventDto event = ReservaNotificacionEventDto.builder()
        .idReserva(reserva.getCodigoReserva())
        .nombreCliente(usuario.getNombres() + " " +usuario.getApellidos())
        .usuarioEmail(usuario.getEmail())
        .dniUsuario(usuario.getDni())
        .total(reserva.getTotal())
        .detalle(detalleReserva)
        .checkIn(reserva.getCheckIn().toString())
        .checkOut(reserva.getCheckOut().toString())
        .build();
    reservaNotificacionProducer.publishReservaNotificacion(event);
  }

  private String generarCodigoReserva() {
    String codigo;
    do {
      codigo = CodigoReservaUtil.generarCodigoReserva();
    } while (reservaRepository.existsByCodigoReserva(codigo));
    return codigo;
  }


}
