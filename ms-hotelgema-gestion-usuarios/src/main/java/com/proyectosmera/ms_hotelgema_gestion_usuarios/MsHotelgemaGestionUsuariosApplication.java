package com.proyectosmera.ms_hotelgema_gestion_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsHotelgemaGestionUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHotelgemaGestionUsuariosApplication.class, args);
	}

}
