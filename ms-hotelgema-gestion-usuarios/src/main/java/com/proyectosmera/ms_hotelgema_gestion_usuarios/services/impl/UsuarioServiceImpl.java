package com.proyectosmera.ms_hotelgema_gestion_usuarios.services.impl;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.UsuarioRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.UsuarioResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.exceptions.ResourceNotFoundException;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.mappers.UsuarioMapper;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.entity.UsuarioEntity;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.repository.UsuarioRepository;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.services.JwtService;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.services.UsuarioService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
  private final UsuarioRepository usuarioRepository;
  private final UsuarioMapper usuarioMapper;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;


  @Override
  public UsuarioResponseDto crearUsuario(UsuarioRequestDto usuarioRequestDto) {
    return usuarioMapper.toResponseDto(usuarioRepository.save(usuarioMapper.toEntity(usuarioRequestDto)));
  }

  @Override
  public Page<UsuarioResponseDto> listarUsuarios(Pageable pageable, String nombre) {
    Page<UsuarioEntity> usuarios;

    if (nombre != null) {
      usuarios = this.usuarioRepository.findByNombresContainingIgnoreCase(nombre, pageable);
    } else {
      usuarios = this.usuarioRepository.findAll(pageable);
    }

    return usuarios.map(usuarioMapper::toResponseDto);
  }

  @Override
  public UsuarioEntity buscarUsuarioPorId(Integer id) {
    UsuarioEntity usuarioEncontrado = this.usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    return usuarioEncontrado;
  }

  @Override
  public UsuarioResponseDto actualizarUsuario(UsuarioRequestDto usuarioRequestDto,Integer id) {
    //buscamos al usuario
    UsuarioEntity usuarioEncontrado = usuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado"));
    //seteamos al usuario
    usuarioMapper.updateUsuarioEntity(usuarioRequestDto, usuarioEncontrado);
    UsuarioEntity usuarioGuardado = usuarioRepository.save(usuarioEncontrado);
    return usuarioMapper.toResponseDto(usuarioGuardado);
  }

  @Override
  public UsuarioResponseDto obtenerPorUsername(String username) {
    UsuarioEntity usuario = usuarioRepository.findByUser(username).orElse(null);
    if (usuario == null) {
      return null;
    }
    return usuarioMapper.toResponseDto(usuario);
  }

  @Override
  public String getUsernameFromToken(String token) {
    try {
      return jwtService.obtenerUsuario(token);
    } catch (Exception e) {
      return null;
    }
  }
  @Override
  public UsuarioResponseDto buscarUsuarioPorDni(String dni) {
    return usuarioRepository.findByDni(dni)
        .map(usuarioMapper::toResponseDto)
        .orElseThrow(() ->
            new ResourceNotFoundException("Usuario con DNI " + dni + " no encontrado")
        );
  }
}
