package com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.client;

import com.proyectosmera.ms_hotelgema_gestion_reservas.config.LoadBalancerConfig;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.dto.habitacion.HabitacionResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.infrastructure.feign.fallback.HabitacionClientFallback;
import java.util.List;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(name = "ms-hotelgema-gestion-habitaciones", fallback = HabitacionClientFallback.class)
@LoadBalancerClient(name = "ms-hotelgema-gestion-habitaciones", configuration = LoadBalancerConfig.class) //balanceador de cargas
public interface HabitacionClient {
  @GetMapping(path = "/habitaciones")
  List<HabitacionResponseDto> listarHabitaciones();

}
