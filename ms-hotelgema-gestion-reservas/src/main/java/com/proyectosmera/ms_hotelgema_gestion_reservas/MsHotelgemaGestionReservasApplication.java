package com.proyectosmera.ms_hotelgema_gestion_reservas;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsHotelgemaGestionReservasApplication implements CommandLineRunner  {

	@Autowired
	private EurekaClient eurekaClient;

	public static void main(String[] args) {
		SpringApplication.run(MsHotelgemaGestionReservasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.eurekaClient.getAllKnownRegions().forEach(System.out::println);
		System.out.println(this.eurekaClient.getApplication("ms-hotelgema-gestion-habitaciones"));
	}
}
