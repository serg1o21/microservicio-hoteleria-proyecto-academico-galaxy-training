package com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDto {
  private Integer id;
  private String nombre;
  private String descripcion;
  private String beneficios;
}
