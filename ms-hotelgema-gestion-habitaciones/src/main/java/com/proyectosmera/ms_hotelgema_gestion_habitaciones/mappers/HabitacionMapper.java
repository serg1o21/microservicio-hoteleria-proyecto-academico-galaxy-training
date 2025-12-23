package com.proyectosmera.ms_hotelgema_gestion_habitaciones.mappers;

import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.habitacion.HabitacionRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.habitacion.HabitacionResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.entities.HabitacionEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HabitacionMapper {
  @Mapping(target = "categoriaEntity.id", source = "idCategoria")
  HabitacionEntity toEntity(HabitacionRequestDto habitacionRequestDto);

  @Mapping(target = "nombreCategoria", source = "categoriaEntity.nombre")
  @Mapping(target = "numeroHabitacion", source = "habitacionEntity.numHabitacion")
  HabitacionResponseDto toResponseDto(HabitacionEntity habitacionEntity);

  List<HabitacionResponseDto> toHabitacionResponseDtoList(List<HabitacionEntity> habitacionEntityList);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "categoriaEntity.id", source = "idCategoria")
  void updateHabitacionEntity(HabitacionRequestDto habitacionRequestDto, @MappingTarget HabitacionEntity habitacionEntity);
}
