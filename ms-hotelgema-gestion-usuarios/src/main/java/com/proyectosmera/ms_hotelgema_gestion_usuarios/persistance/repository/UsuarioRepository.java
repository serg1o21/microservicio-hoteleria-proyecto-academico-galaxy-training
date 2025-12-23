package com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.repository;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.entity.UsuarioEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
  Page<UsuarioEntity> findByNombresContainingIgnoreCase (String nombre, Pageable pageable);
  Optional<UsuarioEntity> findByUser(String username);
  Optional<UsuarioEntity> findByDni(String dni);
}
