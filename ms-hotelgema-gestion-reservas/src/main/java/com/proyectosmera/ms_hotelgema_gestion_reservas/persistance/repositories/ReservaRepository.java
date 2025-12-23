package com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.repositories;

import com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.entities.ReservaEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Integer> {

  boolean existsByCodigoReserva(String codigo);
}
