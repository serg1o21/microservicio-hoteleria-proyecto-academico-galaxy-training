package com.proyectosmera.ms_hotelgema_gestion_usuarios.controllers;


import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.JwtResponseDTO;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.LoginRequestDTO;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.dto.UsuarioResponseDto;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.services.AuthService;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.services.JwtService;
import com.proyectosmera.ms_hotelgema_gestion_usuarios.services.UsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public AuthController(AuthService authService, JwtService jwtService, UsuarioService usuarioService) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request, HttpServletResponse response) {
        JwtResponseDTO jwtResponse = authService.login(request);

        if (jwtResponse == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }

        String token = jwtService.generarToken(request.getUsuario());

        Cookie cookie = new Cookie("JWT", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // Cambiar a true en producción con HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(3600); // 1 hora
        response.addCookie(cookie);

        return ResponseEntity.ok(jwtResponse);
    }

    // LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("JWT", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Map<String, String> body = new HashMap<>();
        body.put("message", "Sesión cerrada");
        return ResponseEntity.ok(body);
    }

    // OBTENER USUARIO ACTUAL
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDto> me(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWT".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    if (jwtService.validarToken(token)) { // Validar token
                        String username = usuarioService.getUsernameFromToken(token);
                        UsuarioResponseDto usuario = usuarioService.obtenerPorUsername(username);
                        if (usuario != null) {
                            return ResponseEntity.ok(usuario);
                        }
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
