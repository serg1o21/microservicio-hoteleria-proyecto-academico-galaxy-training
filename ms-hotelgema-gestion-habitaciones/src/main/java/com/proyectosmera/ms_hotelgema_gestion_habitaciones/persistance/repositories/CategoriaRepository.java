package com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.repositories;

import com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Integer> {
}
