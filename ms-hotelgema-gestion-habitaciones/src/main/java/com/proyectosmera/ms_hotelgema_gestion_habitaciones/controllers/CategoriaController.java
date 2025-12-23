package com.proyectosmera.ms_hotelgema_gestion_habitaciones.controllers;

import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.categoria.CategoriaRequestDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.dto.categoria.CategoriaResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_habitaciones.services.CategoriaService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {
  private final CategoriaService categoriaService;

  @GetMapping
  public ResponseEntity<List<CategoriaResponseDto>> listarCategorias() {
    return ResponseEntity.ok(categoriaService.listarCategorias());
  }

  @PostMapping
  public ResponseEntity<CategoriaResponseDto> crearCategoria(@Valid @RequestBody CategoriaRequestDto categoriaRequestDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crearCategoria(categoriaRequestDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoriaResponseDto> actualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaRequestDto categoriaRequestDto) {
    return ResponseEntity.ok(categoriaService.actualizarCategoria(id,categoriaRequestDto));
  }

}
