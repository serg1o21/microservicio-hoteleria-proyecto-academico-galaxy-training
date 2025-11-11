package com.proyectosmera.ms_hotelgema_gestion_usuarios.infrastructure.adapters.out.persistence.repository;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.infrastructure.adapters.out.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
  UsuarioEntity findUsuarioEntityById(Integer id);
  UsuarioEntity findUsuarioEntityByDni(String dni);
}
