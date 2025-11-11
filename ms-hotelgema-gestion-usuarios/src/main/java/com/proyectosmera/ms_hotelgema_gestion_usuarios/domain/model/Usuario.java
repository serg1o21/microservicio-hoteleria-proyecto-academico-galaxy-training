package com.proyectosmera.ms_hotelgema_gestion_usuarios.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
  private Integer id;
  private String dni;
  private String nombres;
  private String apellidos;
  private Boolean estado;
}
