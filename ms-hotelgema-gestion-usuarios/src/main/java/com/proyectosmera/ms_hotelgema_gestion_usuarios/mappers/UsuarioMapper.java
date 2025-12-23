package com.proyectosmera.ms_hotelgema_gestion_usuarios.mappers;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.UsuarioRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.UsuarioResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.entity.UsuarioEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioMapper {
  @Mapping(target = "id", ignore = true)
  UsuarioEntity toEntity( UsuarioRequestDto usuarioRequestDto);
  UsuarioResponseDto toResponseDto(UsuarioEntity usuarioEntity);
  List<UsuarioResponseDto> toUsuarioResponseDtoList(List<UsuarioEntity> usuarioEntityList);
  @Mapping(target = "id", ignore = true)
  void updateUsuarioEntity(UsuarioRequestDto usuarioRequestDto, @MappingTarget UsuarioEntity usuarioEntity);
}
