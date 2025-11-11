package com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.in;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.domain.model.Usuario;
import java.util.List;

public interface GetUsuarioUseCase {
  List<Usuario> listUsuarios();
  Usuario getUsuarioById(int id);
  Usuario getUsuarioByDNI(String dni);
}
