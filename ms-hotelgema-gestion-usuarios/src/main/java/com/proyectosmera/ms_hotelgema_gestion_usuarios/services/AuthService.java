package com.proyectosmera.ms_hotelgema_gestion_usuarios.services;


import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.JwtResponseDTO;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.LoginRequestDTO;

public interface AuthService {
JwtResponseDTO login(LoginRequestDTO req);

}
