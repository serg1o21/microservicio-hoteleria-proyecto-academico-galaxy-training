package com.proyectosmera.ms_hotelgema_gestion_reservas.services;

import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.habitacion.HabitacionResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.DetalleReservaResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.ReservaRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.ReservaResponseDto;
import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

  //consultar disponibilidad
  List<HabitacionResponseDto> listarHabitacionesDisponibles(LocalDate checkIn, LocalDate checkOut);
  //listar reservas
  List<ReservaResponseDto> listarReservas();
  //listar detalles por reserva
  List<DetalleReservaResponseDto> listarDetallesPorReserva(Integer idReserva);
  //crear reserva
  ReservaResponseDto crearReserva(ReservaRequestDto reservaRequestDto);
  //actualizar reserva
  ReservaResponseDto actualizarReserva(Integer id, ReservaRequestDto reservaRequestDto);

}
