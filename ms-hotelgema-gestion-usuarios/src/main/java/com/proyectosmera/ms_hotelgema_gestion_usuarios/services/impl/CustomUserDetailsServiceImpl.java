package com.proyectosmera.ms_hotelgema_gestion_usuarios.services.impl;


import com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.entity.UsuarioEntity;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.persistance.repository.UsuarioRepository;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.security.UsuarioPrincipal;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.services.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return UsuarioPrincipal.build(usuario); // Devuelve tu clase con id, username, rol, etc.
    }

}
