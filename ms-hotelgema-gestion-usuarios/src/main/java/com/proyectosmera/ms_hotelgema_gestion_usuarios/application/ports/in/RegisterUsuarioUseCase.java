package com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.in;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.domain.model.Usuario;

public interface RegisterUsuarioUseCase {
  Usuario saveUsuario(Usuario usuario);
}
