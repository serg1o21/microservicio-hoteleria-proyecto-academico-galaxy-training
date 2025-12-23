package com.proyectosmera.ms_hotelgema_gestion_habitaciones.controllers;

import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.habitacion.HabitacionRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.habitacion.HabitacionResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.services.HabitacionService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habitaciones")
@RequiredArgsConstructor
public class HabitacionController {

  private final HabitacionService habitacionService;

  @GetMapping
  public ResponseEntity<List<HabitacionResponseDto>> listarHabitaciones() {
    return ResponseEntity.ok(habitacionService.listarHabitaciones());
  }

  @PostMapping
  public ResponseEntity<HabitacionResponseDto> crearHabitacion(@Valid  @RequestBody HabitacionRequestDto habitacionRequestDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(habitacionService.crearHabitacion(habitacionRequestDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<HabitacionResponseDto> actualizarHabitacion(@PathVariable Integer id, @RequestBody HabitacionRequestDto habitacionRequestDto) {
    return ResponseEntity.ok(habitacionService.actualizarHabitacion(id,habitacionRequestDto));
  }

}
