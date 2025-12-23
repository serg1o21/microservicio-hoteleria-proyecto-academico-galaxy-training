package com.proyectosmera.ms_hotelgema_gestion_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDTO {
    private String usuario;
    private String rol;
}