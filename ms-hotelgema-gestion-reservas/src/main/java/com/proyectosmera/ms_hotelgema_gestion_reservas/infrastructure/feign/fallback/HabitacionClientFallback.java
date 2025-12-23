package com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.fallback;

import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.client.HabitacionClient;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.habitacion.HabitacionResponseDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HabitacionClientFallback implements HabitacionClient {
  @Override
  public List<HabitacionResponseDto> listarHabitaciones() {
    log.warn("Fallback activado: ms-hotelgema-gestion-habitaciones no disponible");
    return List.of();
  }
}
