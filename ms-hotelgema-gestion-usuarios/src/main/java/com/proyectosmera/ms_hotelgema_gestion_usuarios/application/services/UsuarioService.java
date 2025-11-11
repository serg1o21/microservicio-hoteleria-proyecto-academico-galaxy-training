package com.proyectosmera.ms_hotelgema_gestion_usuarios.application.services;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.in.GetUsuarioUseCase;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.in.RegisterUsuarioUseCase;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.in.UpdateUsuarioUseCase;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.out.UsuarioPersistencePort;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.domain.model.Usuario;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements GetUsuarioUseCase, RegisterUsuarioUseCase, UpdateUsuarioUseCase {

  //inyectamos la dependencia del usuariorepository
  private final UsuarioPersistencePort usuarioPersistencePort;

  @Override
  public List<Usuario> listUsuarios() {
    return usuarioPersistencePort.listUsuarios();
  }
  @Override
  public Usuario getUsuarioById(int id) {
    return usuarioPersistencePort.getUsuarioById(id);
  }
  @Override
  public Usuario getUsuarioByDNI(String dni) {
    return usuarioPersistencePort.getUsuarioByDNI(dni);
  }
  @Override
  public Usuario saveUsuario(Usuario usuario) {
    return usuarioPersistencePort.saveUsuario(usuario);
  }
  @Override
  public Usuario updateUsuario(Integer id, Usuario usuario) {
      return usuarioPersistencePort.updateUsuario(id,usuario);
  }
}
