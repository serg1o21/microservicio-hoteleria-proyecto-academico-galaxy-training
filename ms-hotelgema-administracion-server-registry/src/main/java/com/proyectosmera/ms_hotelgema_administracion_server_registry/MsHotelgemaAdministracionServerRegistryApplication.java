package com.proyectosmera.ms_hotelgema_administracion_server_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsHotelgemaAdministracionServerRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHotelgemaAdministracionServerRegistryApplication.class, args);
	}

}
