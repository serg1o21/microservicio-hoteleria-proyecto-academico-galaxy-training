package com.proyectosmera.ms_hotelgema_gestion_reservas.mappers;

import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.DetalleReservaResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.entities.DetalleReservaEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DetalleReservaMapper {

  @Mapping(target = "reserva", source = "reservaEntity.id")
  @Mapping(target = "numeroHabitacion", source = "numHabitacion")
  @Mapping(target = "precioPorNoche", source = "precio")
  DetalleReservaResponseDto toResponseDto(DetalleReservaEntity detalleReservaEntity);
  @Mapping(target = "reserva", source = "reservaEntity.id")
  @Mapping(target = "numeroHabitacion", source = "numHabitacion")
  @Mapping(target = "precioPorNoche", source = "precio")
  List<DetalleReservaResponseDto> toDetalleReservaResponseDtoList(List<DetalleReservaEntity> detalleReservaEntityList);
}
