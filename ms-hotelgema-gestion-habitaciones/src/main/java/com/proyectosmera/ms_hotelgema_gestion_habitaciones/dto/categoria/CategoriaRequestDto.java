package com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.categoria;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDto {

  private Integer id;
  @NotBlank(message = "El nombre de la categoría no puede estar vacío")
  private String nombre;
  @NotBlank(message = "La descripción de la categoría no puede estar vacío")
  private String descripcion;
  @NotBlank(message = "Los beneficios de la categoría no puede estar vacío")
  @Size(min = 5, message = "Los beneficios deben tener al menos 5 caracteres")
  private String beneficios;
}
