package com.proyectosmera.ms_hotelgema_gestion_habitaciones.services;

import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.categoria.CategoriaRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.categoria.CategoriaResponseDto;
import java.util.List;

public interface CategoriaService {
  CategoriaResponseDto crearCategoria(CategoriaRequestDto categoriaRequestDto);
  List<CategoriaResponseDto> listarCategorias();
  CategoriaResponseDto actualizarCategoria(Integer id, CategoriaRequestDto categoriaRequestDto);
}
