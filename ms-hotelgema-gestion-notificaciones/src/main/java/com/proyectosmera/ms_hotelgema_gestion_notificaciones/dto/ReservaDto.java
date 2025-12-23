package com.proyectosmera.ms_hotelgema_gestion_notificaciones.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto {
  private String idReserva;
  private String nombreCliente;
  private String usuarioEmail;
  private String hotelEmail;
  private String dniUsuario;
  private String checkIn;
  private String checkOut;
  private Double total;
  private List<DetalleDto> detalle;

  // getters y setters
  // ...
}