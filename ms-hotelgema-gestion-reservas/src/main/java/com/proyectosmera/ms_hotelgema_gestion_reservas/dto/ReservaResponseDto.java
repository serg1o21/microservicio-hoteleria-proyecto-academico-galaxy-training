package com.proyectosmera.ms_hotelgema_gestion_reservas.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResponseDto {
  private String dni;
  private String codigo;
  private LocalDate checkIn;
  private LocalDate checkOut;
  private LocalDateTime fechaReserva;
  private String estado;
  private Double total;
}
