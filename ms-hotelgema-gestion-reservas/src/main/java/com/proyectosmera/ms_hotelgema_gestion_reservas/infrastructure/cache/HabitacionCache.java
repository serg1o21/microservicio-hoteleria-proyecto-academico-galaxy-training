package com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.cache;

import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.habitacion.HabitacionResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class HabitacionCache {

  private volatile List<HabitacionResponseDto> cache = List.of();
  private volatile LocalDateTime lastUpdate;

  public List<HabitacionResponseDto> get() {
    return cache;
  }

  public void update(List<HabitacionResponseDto> data) {
    if (data != null && !data.isEmpty()) {
      this.cache = data;
      this.lastUpdate = LocalDateTime.now();
    }
  }

  public boolean hasData() {
    return cache != null && !cache.isEmpty();
  }

  public boolean isExpired(int minutes) {
    return lastUpdate == null ||
        lastUpdate.isBefore(LocalDateTime.now().minusMinutes(minutes));
  }
}