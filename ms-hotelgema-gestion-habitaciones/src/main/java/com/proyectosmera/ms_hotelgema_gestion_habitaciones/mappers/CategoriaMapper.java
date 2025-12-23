package com.proyectosmera.ms_hotelgema_gestion_habitaciones.mappers;

import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.categoria.CategoriaRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.categoria.CategoriaResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.entities.CategoriaEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoriaMapper {
  CategoriaEntity toEntity(CategoriaRequestDto categoriaRequestDto);
  CategoriaResponseDto toResponseDto(CategoriaEntity categoriaEntity);
  List<CategoriaResponseDto> toCategoriaResponseDtoList(List<CategoriaEntity> categoriaEntityList);
  @Mapping(target = "id", ignore = true)
  void updateCategoriaEntity(CategoriaRequestDto categoriaRequestDto, @MappingTarget CategoriaEntity categoriaEntity);

}
