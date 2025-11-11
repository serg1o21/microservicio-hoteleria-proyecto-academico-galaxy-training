package com.proyectosmera.ms_hotelgema_gestion_usuarios.infrastructure.adapters.out.persistence;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.out.UsuarioPersistencePort;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.domain.model.Usuario;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.infrastructure.adapters.out.persistence.entity.UsuarioEntity;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.infrastructure.adapters.out.persistence.mapper.UsuarioPersistenceMapper;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.infrastructure.adapters.out.persistence.repository.UsuarioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioPersistenceAdapter implements UsuarioPersistencePort {

  private final UsuarioRepository usuarioRepository;
  private final UsuarioPersistenceMapper usuarioPersistenceMapper;
  @Override
  public List<Usuario> listUsuarios() {
  return usuarioPersistenceMapper.toUsuarioList(usuarioRepository.findAll());
  }

  @Override
  public Usuario getUsuarioById(Integer id) {
    return usuarioPersistenceMapper.toUsuario(usuarioRepository.findUsuarioEntityById(id));
  }

  @Override
  public Usuario getUsuarioByDNI(String dni) {
    return usuarioPersistenceMapper.toUsuario(usuarioRepository.findUsuarioEntityByDni(dni));
  }

  @Override
  public Usuario saveUsuario(Usuario usuario) {
    return usuarioPersistenceMapper.toUsuario(usuarioRepository.save(usuarioPersistenceMapper.toUsuarioEntity(usuario)));
  }

  @Override
  public Usuario updateUsuario(Integer id, Usuario usuario) {
    //buscamos por id
    UsuarioEntity usuarioEncontrado =  usuarioRepository.findUsuarioEntityById(id);
    System.out.println("se encontro al usuario " + usuarioEncontrado);
    //actualizamos
    usuarioPersistenceMapper.updateUsuarioEntity(usuario, usuarioEncontrado);
    UsuarioEntity usuarioGuardado = usuarioRepository.save(usuarioEncontrado);
    return usuarioPersistenceMapper.toUsuario(usuarioGuardado);
  }
}
