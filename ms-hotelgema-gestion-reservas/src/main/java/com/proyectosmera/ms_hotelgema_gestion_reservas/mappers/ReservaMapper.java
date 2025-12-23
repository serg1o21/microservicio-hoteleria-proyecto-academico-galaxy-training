package com.proyectosmera.ms_hotelgema_gestion_reservas.mappers;

import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.ReservaRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.dto.ReservaResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_reservas.persistance.entities.ReservaEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReservaMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "codigoReserva", ignore = true)
  ReservaEntity toEntity(ReservaRequestDto reservaRequestDto);

  @Mapping(target = "codigo", source = "codigoReserva")
  ReservaResponseDto toResponseDto(ReservaEntity reservaEntity);

  List<ReservaResponseDto> toReservaResponseDtoList(List<ReservaEntity> reservaEntityList);
  @Mapping(target = "codigoReserva", ignore = true)
  void updateReservaEntity(ReservaRequestDto reservaRequestDto, @MappingTarget ReservaEntity reservaEntity);
}
