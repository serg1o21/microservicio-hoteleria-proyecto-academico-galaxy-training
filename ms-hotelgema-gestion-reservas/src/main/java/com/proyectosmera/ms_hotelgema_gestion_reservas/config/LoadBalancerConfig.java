package com.proyectosmera.ms_hotelgema_gestion_reservas.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
public class LoadBalancerConfig {

  @Bean
  public ServiceInstanceListSupplier serviceInstanceListSuppliers(ConfigurableApplicationContext context ) {
    log.info("configurando balanceador de cargas");
    return ServiceInstanceListSupplier
        .builder()
        .withBlockingDiscoveryClient()
        .build(context);
  }
}
