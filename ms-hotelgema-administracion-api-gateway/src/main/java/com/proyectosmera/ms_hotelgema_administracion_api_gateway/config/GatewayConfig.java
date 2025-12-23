package com.proyectosmera.ms_hotelgema_administracion_api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GatewayConfig {
//  @Bean
//  @Profile(value = "eureka-of")
//  public RouteLocator routeLocatorEurekaOff(RouteLocatorBuilder builder) {
//    return builder
//        .routes()
//        .route(route -> route
//            .path(""))
//  }
  @Bean
  @Profile(value = "eureka-on")
  public RouteLocator routeLocatorEurekaOn(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route(route -> route
            .path("/reservas/**")
            .uri("lb://ms-hotelgema-gestion-reservas")
        ).route(route -> route
            .path("/habitaciones/**")
            .uri("lb://ms-hotelgema-gestion-habitaciones")
        ).route(route -> route
            .path("/usuarios/**")
            .uri("lb://ms-hotelgema-gestion-usuarios")
        )
        .build();
  }
}
