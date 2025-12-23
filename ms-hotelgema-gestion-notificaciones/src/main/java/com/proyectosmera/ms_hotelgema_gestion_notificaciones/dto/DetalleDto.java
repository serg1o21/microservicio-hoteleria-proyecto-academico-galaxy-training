package com.proyectosmera.ms_hotelgema_gestion_notificaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleDto {
  private Integer id;
  private Integer reserva;
  private String numeroHabitacion;
  private Integer dias;
  private Double precioPorNoche;
  private Double subtotal;
}
