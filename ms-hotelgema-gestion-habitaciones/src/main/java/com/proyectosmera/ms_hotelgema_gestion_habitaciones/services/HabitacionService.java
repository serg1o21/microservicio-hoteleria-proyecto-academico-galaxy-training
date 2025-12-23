package com.proyectosmera.ms_hotelgema_gestion_habitaciones.services;

import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.habitacion.HabitacionRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.habitacion.HabitacionResponseDto;
import java.util.List;

public interface HabitacionService {
  HabitacionResponseDto crearHabitacion(HabitacionRequestDto habitacionRequestDto);
  List<HabitacionResponseDto> listarHabitaciones();
  HabitacionResponseDto actualizarHabitacion(Integer id, HabitacionRequestDto habitacionRequestDto);
}
