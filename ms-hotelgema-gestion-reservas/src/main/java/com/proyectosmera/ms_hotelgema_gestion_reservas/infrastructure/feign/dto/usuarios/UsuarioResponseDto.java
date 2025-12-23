package com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto {
  private String dni;
  private String nombres;
  private String apellidos;
  private String email;
  private Boolean estado;
}
