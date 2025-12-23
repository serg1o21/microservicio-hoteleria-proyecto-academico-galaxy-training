package com.proyectosmera.ms_hotelgema_gestion_reservas.dto;

import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.habitacion.HabitacionRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequestDto {
  private Integer id;
  @NotNull(message = "La fecha de check-in es obligatoria")
  private LocalDate checkIn;
  @NotNull(message = "La fecha de check-out es obligatoria")
  private LocalDate checkOut;
  @NotBlank(message = "El dni es obligatorio")
  @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
  private String dni;
  private String estado;
  private LocalDateTime fechaReserva;
  private Double total;
  @NotEmpty(message = "La lista de habitaciones es requerida")
  private List<HabitacionRequestDto> habitaciones;
}
