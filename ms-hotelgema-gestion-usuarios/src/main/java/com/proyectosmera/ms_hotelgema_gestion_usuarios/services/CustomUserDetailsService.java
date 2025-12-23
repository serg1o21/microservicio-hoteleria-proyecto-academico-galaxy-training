package com.proyectosmera.ms_hotelgema_gestion_usuarios.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
