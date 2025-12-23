package com.proyectosmera.ms_hotelgema_gestion_reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleReservaResponseDto {
  private Integer id;
  private Integer reserva;
  private String numeroHabitacion;
  private Integer dias;
  private Double precioPorNoche;
  private Double subtotal;
}
