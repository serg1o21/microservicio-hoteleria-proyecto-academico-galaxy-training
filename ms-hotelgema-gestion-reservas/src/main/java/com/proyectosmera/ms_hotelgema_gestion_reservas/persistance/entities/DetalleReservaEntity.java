package com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_reservas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleReservaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String numHabitacion;
  private Integer dias;
  private Double precio;
  private Double subtotal;
  @ManyToOne(optional = false)
  @JoinColumn(name = "id_reserva" , referencedColumnName = "id")
  private ReservaEntity reservaEntity;

}
