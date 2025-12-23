package com.proyectosmera.ms_hotelgema_gestion_reservas.controllers;

import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.habitacion.HabitacionResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.DetalleReservaResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.ReservaRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.ReservaResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.services.ReservaService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class ReservaController {
  private final ReservaService reservaService;

  @GetMapping
    public ResponseEntity<List<ReservaResponseDto>> listarReservas() {
      return ResponseEntity.ok(reservaService.listarReservas());
    }

  @GetMapping("/disponibles")
  public ResponseEntity<List<HabitacionResponseDto>> listarDisponibles(
      @RequestParam("checkIn") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
      @RequestParam("checkOut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {
    return ResponseEntity.ok(reservaService.listarHabitacionesDisponibles(checkIn, checkOut));
  }

  @GetMapping("/{id}/detalles")
  public ResponseEntity<List<DetalleReservaResponseDto>> listarDetallesPorReserva(@PathVariable Integer id) {
    return ResponseEntity.ok(reservaService.listarDetallesPorReserva(id));
  }

  @PostMapping
  public ResponseEntity<ReservaResponseDto> crearReserva(@Valid @RequestBody ReservaRequestDto reservaRequestDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crearReserva(reservaRequestDto));
  }


}
