package com.proyectosmera.ms_hotelgema_gestion_usuarios.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String usuario;
    private String password;
}