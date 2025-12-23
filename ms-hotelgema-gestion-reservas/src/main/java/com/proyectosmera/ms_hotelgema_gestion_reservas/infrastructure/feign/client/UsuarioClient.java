package com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.client;

import com.proyectosmera.ms_hotelgema_gestion_reservas.config.LoadBalancerConfig;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.usuarios.UsuarioResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.fallback.HabitacionClientFallback;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.fallback.UsuarioClientFallback;
import java.util.List;
import java.util.Optional;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-hotelgema-gestion-usuarios", fallback = UsuarioClientFallback.class)
@LoadBalancerClient(name = "ms-hotelgema-gestion-usuarios", configuration = LoadBalancerConfig.class)
public interface UsuarioClient {
  @GetMapping(path = "/usuarios/{dni}")
  Optional<UsuarioResponseDto> buscarUsuarioPorDni(@PathVariable String dni);

}
