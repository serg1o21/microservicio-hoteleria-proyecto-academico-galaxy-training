package com.proyectosmera.ms_hotelgema_gestion_habitaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsHotelgemaGestionHabitacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHotelgemaGestionHabitacionesApplication.class, args);
	}

}
