package com.proyectosmera.ms_hotelgema_gestion_usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDto {
  @NotBlank(message = "El DNI es obligatorio")
  @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
  private String dni;

  @NotBlank(message = "Los nombres son obligatorios")
  @Size(min = 3, max = 100, message = "Los nombres deben tener entre 3 y 100 caracteres")
  private String nombres;

  @NotBlank(message = "Los apellidos son obligatorios")
  @Size(min = 3, max = 100, message = "Los apellidos deben tener entre 3 y 100 caracteres")
  private String apellidos;

  @NotBlank(message = "El email es obligatorio")
  @Email(message = "Debe ingresar un email válido")
  @Size(max = 150, message = "El email no debe superar los 150 caracteres")
  private String email;

  @NotBlank(message = "El usuario es obligatorio")
  @Size(min = 4, max = 20, message = "El usuario debe tener entre 4 y 20 caracteres")
  @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "El usuario solo puede contener letras, números, puntos, guiones y guion bajo")
  private String user;

  @NotBlank(message = "El rol es obligatorio")
  private String rol;

  @NotBlank(message = "La contraseña es obligatoria")
  @Size(min = 5, max = 10, message = "La contraseña debe tener entre 5 y 10 caracteres")
  @Pattern( regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "La contraseña debe incluir minúsculas, mayúsculas, números y un carácter especial" )
  private String pass;

  private Boolean estado;

}
