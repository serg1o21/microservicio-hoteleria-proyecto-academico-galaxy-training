package com.proyectosmera.ms_hotelgema_gestion_usuarios.services;

public interface JwtService {

    String generarToken(String username);

    String obtenerUsuario(String token);

    boolean validarToken(String token);

}
