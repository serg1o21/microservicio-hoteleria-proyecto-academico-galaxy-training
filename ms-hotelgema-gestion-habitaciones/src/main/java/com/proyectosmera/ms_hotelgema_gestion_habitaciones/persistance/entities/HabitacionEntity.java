package com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.entities;

import jakarta.persistence.Column;
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
@Table(name = "habitaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(unique = true)
  private String numHabitacion;
  private Integer aforo;
  private Double precioNoche;
  private Boolean estado;
  @ManyToOne
  @JoinColumn(name = "categoria_id", referencedColumnName = "id")
  private CategoriaEntity categoriaEntity;
}