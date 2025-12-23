package com.proyectosmera.ms_hotelgema_gestion_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto {

  private Integer id;
  private String dni;
  private String nombres;
  private String apellidos;
  private String email;
  private String user;
  private String rol;
  private Boolean estado;
}
