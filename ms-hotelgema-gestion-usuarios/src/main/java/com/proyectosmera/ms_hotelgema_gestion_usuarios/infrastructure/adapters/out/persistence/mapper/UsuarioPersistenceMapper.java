package com.proyectosmera.ms_hotelgema_gestion_usuarios.infrastructure.adapters.out.persistence.mapper;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.domain.model.Usuario;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.infrastructure.adapters.out.persistence.entity.UsuarioEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UsuarioPersistenceMapper {

   Usuario toUsuario(UsuarioEntity usuarioEntity);
   UsuarioEntity toUsuarioEntity(Usuario usuario);
   @Mapping(target = "id", ignore = true)
   void updateUsuarioEntity(Usuario usuario, @MappingTarget UsuarioEntity usuarioEntity);
   List<Usuario> toUsuarioList(List<UsuarioEntity> usuarioEntityList);
}
