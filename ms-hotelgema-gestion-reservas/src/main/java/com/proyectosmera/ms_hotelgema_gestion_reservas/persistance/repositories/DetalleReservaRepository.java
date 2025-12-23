package com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.repositories;

import com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.entities.DetalleReservaEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleReservaRepository extends JpaRepository<DetalleReservaEntity, Integer> {
  @Query("""
    SELECT DISTINCT d.numHabitacion
    FROM DetalleReservaEntity d
    JOIN d.reservaEntity r
    WHERE r.estado <> 'CANCELADA'
      AND r.checkIn < :checkOut
      AND r.checkOut > :checkIn
""")
  List<String> findHabitacionesOcupadas(LocalDate checkIn, LocalDate checkOut);

  List<DetalleReservaEntity> findByReservaEntity_Id(Integer idReserva);
}
