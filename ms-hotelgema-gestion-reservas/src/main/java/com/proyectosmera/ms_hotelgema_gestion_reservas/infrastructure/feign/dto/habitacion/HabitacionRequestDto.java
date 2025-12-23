package com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.habitacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionRequestDto {
  private Integer id;
  private String numHabitacion;
  private Integer aforo;
  private Double precioNoche;
  private Boolean estado;
  private Integer idCategoria;
}
