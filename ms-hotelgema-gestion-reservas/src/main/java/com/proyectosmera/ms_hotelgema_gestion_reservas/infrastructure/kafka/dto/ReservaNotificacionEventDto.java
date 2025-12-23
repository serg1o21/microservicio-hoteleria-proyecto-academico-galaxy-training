package com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.kafka.dto;

import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.DetalleReservaResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaNotificacionEventDto {
  private String idReserva;
  private String nombreCliente;
  private String usuarioEmail;
  private String dniUsuario;
  private String checkIn;
  private String checkOut;
  private Double total;
  private List<DetalleReservaResponseDto> detalle;
}
