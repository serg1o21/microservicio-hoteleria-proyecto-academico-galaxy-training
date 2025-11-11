package com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.out;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.domain.model.Usuario;
import java.util.List;

public interface UsuarioPersistencePort {

  List<Usuario> listUsuarios();
  Usuario getUsuarioById(Integer id);
  Usuario getUsuarioByDNI(String dni);
  Usuario saveUsuario(Usuario usuario);
  Usuario updateUsuario(Integer id, Usuario usuario);
}
