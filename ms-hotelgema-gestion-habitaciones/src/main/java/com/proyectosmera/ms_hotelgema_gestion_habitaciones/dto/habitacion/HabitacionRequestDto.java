package com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.habitacion;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionRequestDto {
  private Integer id;
  @NotBlank(message = "El número de habitación no puede estar vacío")
  private String numHabitacion;
  @NotNull(message = "El aforo es obligatorio")
  @Min(value = 1, message = "El aforo mínimo es 1 persona")
  private Integer aforo;
  @NotNull(message = "El precio por noche es obligatorio")
  @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
  private Double precioNoche;
  @NotNull(message = "El estado es obligatorio")
  private Boolean estado;
  @NotNull(message = "La categoría es obligatoria")
  private Integer idCategoria;
}
