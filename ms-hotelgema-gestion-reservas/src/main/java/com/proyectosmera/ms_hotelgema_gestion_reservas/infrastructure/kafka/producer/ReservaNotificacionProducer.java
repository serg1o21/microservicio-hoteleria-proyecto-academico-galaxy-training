package com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.kafka.dto.ReservaNotificacionEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservaNotificacionProducer {

  private final StreamBridge streamBridge;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public void publishReservaNotificacion(ReservaNotificacionEventDto dto) {
    try {
      String mensaje = objectMapper.writeValueAsString(dto);
      streamBridge.send("producerReservaNotificacion-out-0", mensaje);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error serializando evento de reserva", e);
    }
  }
}
