package com.proyectosmera.ms_hotelgema_gestion_usuarios.infrastructure.adapters.in.rest.controller;

import com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.in.GetUsuarioUseCase;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.in.RegisterUsuarioUseCase;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.application.ports.in.UpdateUsuarioUseCase;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.domain.model.Usuario;
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
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {
  private final GetUsuarioUseCase getUsuarioUseCase;
  private final RegisterUsuarioUseCase registerUsuarioUseCase;
  private final UpdateUsuarioUseCase updateUsuarioUseCase;
  @GetMapping
  public ResponseEntity<?> listUsuarios() {
    List<Usuario> lista= getUsuarioUseCase.listUsuarios();
    return ResponseEntity.ok(lista);
  }
  @PostMapping
  public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
    return ResponseEntity.status(HttpStatus.CREATED).body(registerUsuarioUseCase.saveUsuario(usuario));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
    return ResponseEntity.status(HttpStatus.CREATED).body(updateUsuarioUseCase.updateUsuario(id,usuario));
  }
}
