package com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.repositories;

import com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.entities.HabitacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<HabitacionEntity, Integer> {
}
