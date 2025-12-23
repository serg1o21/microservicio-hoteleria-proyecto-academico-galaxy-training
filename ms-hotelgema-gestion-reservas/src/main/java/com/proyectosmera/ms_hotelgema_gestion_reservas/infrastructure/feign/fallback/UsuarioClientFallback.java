package com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.fallback;

import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.client.UsuarioClient;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.usuarios.UsuarioResponseDto;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UsuarioClientFallback implements UsuarioClient {
  @Override
  public Optional<UsuarioResponseDto> buscarUsuarioPorDni(String dni) {
    log.warn("Fallback activado: ms-hotelgema-gestion-habitaciones no disponible");
    return Optional.empty();
  }
}
