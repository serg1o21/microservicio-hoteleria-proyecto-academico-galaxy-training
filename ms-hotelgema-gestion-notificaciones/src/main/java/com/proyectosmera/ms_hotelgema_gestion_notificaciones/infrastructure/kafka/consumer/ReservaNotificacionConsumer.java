package com.proyectosmera.ms_hotelgema_gestion_notificaciones.infrastructure.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyectosmera.ms_hotelgema_gestion_notificaciones.dto.ReservaDto;
import com.proyectosmera.ms_hotelgema_gestion_notificaciones.services.EmailService;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j

public class ReservaNotificacionConsumer {
  private final EmailService emailService;
  private final ObjectMapper objectMapper;

  public ReservaNotificacionConsumer(EmailService emailService, ObjectMapper objectMapper) {
    this.emailService = emailService;
    this.objectMapper = objectMapper;
  }

  @Bean
  public Consumer<String> consumerReservaNotificacion() {
    return report -> {
      try {
        ReservaDto reserva = objectMapper.readValue(report, ReservaDto.class);
        log.info("Mensaje recibido: {}", reserva);
        // Enviar email al usuario
        emailService.sendReservaEmail(
            reserva.getUsuarioEmail(),
            reserva.getNombreCliente(), // podrías usar nombre si lo agregas
            reserva.getIdReserva().toString(),
            reserva.getCheckIn(),
            reserva.getCheckOut(),
            reserva.getTotal(),
            reserva.getDetalle()
        );

        log.info("Email enviado a {}", reserva.getUsuarioEmail());

      } catch (Exception e) {
        log.error("Error procesando mensaje Kafka: {}", e.getMessage(), e);
      }
    };
  }
}
