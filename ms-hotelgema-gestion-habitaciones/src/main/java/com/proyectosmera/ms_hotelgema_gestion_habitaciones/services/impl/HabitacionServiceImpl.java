package com.proyectosmera.ms_hotelgema_gestion_habitaciones.services.impl;

import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.habitacion.HabitacionRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.habitacion.HabitacionResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.mappers.HabitacionMapper;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.entities.HabitacionEntity;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.repositories.CategoriaRepository;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.repositories.HabitacionRepository;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.services.HabitacionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HabitacionServiceImpl implements HabitacionService {
  private final HabitacionRepository habitacionRepository;
  private final CategoriaRepository categoriaRepository;
  private final HabitacionMapper habitacionMapper;
  @Override
  public HabitacionResponseDto crearHabitacion(HabitacionRequestDto habitacionRequestDto) {
    HabitacionEntity habitacionGuardada = habitacionMapper.toEntity(habitacionRequestDto);
    //buscamos la categoria asginada
    var categoriaEncontrada = categoriaRepository.findById(habitacionRequestDto.getIdCategoria()).orElseThrow(()-> new RuntimeException("No se encontró la categoría"));
    habitacionGuardada.setCategoriaEntity(categoriaEncontrada);
    return habitacionMapper.toResponseDto(habitacionRepository.save(habitacionGuardada));
  }

  @Override
  public List<HabitacionResponseDto> listarHabitaciones() {
    return habitacionMapper.toHabitacionResponseDtoList(habitacionRepository.findAll());
  }

  @Override
  public HabitacionResponseDto actualizarHabitacion(Integer id, HabitacionRequestDto habitacionRequestDto) {
    //buscamos la habitacion
    var habitacionEncontrada = habitacionRepository.findById(id).orElseThrow(()-> new RuntimeException("no se encontró la habitación"));
    log.info("Habitacion encontrada :" +habitacionEncontrada);
    //seteamos
    habitacionMapper.updateHabitacionEntity(habitacionRequestDto,habitacionEncontrada);
    //guardamos
    var habitacionGuardada = habitacionRepository.save(habitacionEncontrada);
    return habitacionMapper.toResponseDto(habitacionGuardada);
  }
}
