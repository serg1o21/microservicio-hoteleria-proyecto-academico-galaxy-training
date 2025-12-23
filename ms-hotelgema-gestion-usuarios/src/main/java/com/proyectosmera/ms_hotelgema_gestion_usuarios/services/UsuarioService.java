package com.proyectosmera.ms_hotelgema_gestion_usuarios.services;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.UsuarioRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.UsuarioResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.entity.UsuarioEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
  Page<UsuarioResponseDto> listarUsuarios(Pageable pageable, String nombre);

  UsuarioEntity buscarUsuarioPorId(Integer id);

  UsuarioResponseDto crearUsuario(UsuarioRequestDto usuarioRequestDTO);

  UsuarioResponseDto actualizarUsuario(UsuarioRequestDto usuarioRequestDTO, Integer id);

  UsuarioResponseDto obtenerPorUsername(String username);

  String getUsernameFromToken(String token);

  UsuarioResponseDto buscarUsuarioPorDni(String dni);
}
