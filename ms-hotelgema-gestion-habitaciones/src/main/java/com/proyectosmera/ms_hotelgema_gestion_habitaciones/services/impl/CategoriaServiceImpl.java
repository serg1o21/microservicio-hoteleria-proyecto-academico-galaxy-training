package com.proyectosmera.ms_hotelgema_gestion_habitaciones.services.impl;

import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.categoria.CategoriaRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.categoria.CategoriaResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.mappers.CategoriaMapper;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.persistance.repositories.CategoriaRepository;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.services.CategoriaService;
import java.util.List;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor

public class CategoriaServiceImpl implements CategoriaService {

  private final CategoriaRepository categoriaRepository;
  private final CategoriaMapper categoriaMapper;

  @Override
  public CategoriaResponseDto crearCategoria(CategoriaRequestDto categoriaRequestDto) {
    return categoriaMapper.toResponseDto(categoriaRepository.save(categoriaMapper.toEntity(categoriaRequestDto)));
  }

  @Override
  public List<CategoriaResponseDto> listarCategorias() {
    return categoriaMapper.toCategoriaResponseDtoList(categoriaRepository.findAll());
  }

  @Override
  public CategoriaResponseDto actualizarCategoria(Integer id, CategoriaRequestDto categoriaRequestDto) {
    //buscamos la categoria en cuestion
    var categoriaEncontrada= categoriaRepository.findById(id).orElseThrow( ()-> new RuntimeException("No se encontro la categoria"));
    log.info("categoria encontrada: " + categoriaEncontrada);
    //seteamos
    categoriaMapper.updateCategoriaEntity(categoriaRequestDto,categoriaEncontrada);
    //guardamos
    var categoriaGuardada = categoriaRepository.save(categoriaEncontrada);
    return categoriaMapper.toResponseDto(categoriaGuardada);
  }
}
