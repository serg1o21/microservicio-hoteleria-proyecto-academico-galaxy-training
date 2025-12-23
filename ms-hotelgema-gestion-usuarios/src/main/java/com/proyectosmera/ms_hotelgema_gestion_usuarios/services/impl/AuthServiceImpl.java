package com.proyectosmera.ms_hotelgema_gestion_usuarios.services.impl;


import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.JwtResponseDTO;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.LoginRequestDTO;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.entity.UsuarioEntity;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.repository.UsuarioRepository;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.services.AuthService;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.services.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;



    public AuthServiceImpl(UsuarioRepository usuarioRepository, JwtService jwtService, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @Override
    public JwtResponseDTO login(LoginRequestDTO req) {
      UsuarioEntity usuario = this.usuarioRepository.findByUser(req.getUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!encoder.matches(req.getPassword(), usuario.getPass())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generarToken(usuario.getUser());
        return new JwtResponseDTO(usuario.getUser(), usuario.getRol());
    }

    
}