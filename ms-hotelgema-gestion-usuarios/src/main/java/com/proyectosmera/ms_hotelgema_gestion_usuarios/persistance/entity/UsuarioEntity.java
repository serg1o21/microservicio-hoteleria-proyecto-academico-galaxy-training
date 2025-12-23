package com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(unique = true)
  private String dni;
  private String nombres;
  private String apellidos;
  @Column(unique = true)
  private String email;
  @Column(unique = true)
  private String user;
  private String pass;
  private String rol;
  private Boolean estado;
}
