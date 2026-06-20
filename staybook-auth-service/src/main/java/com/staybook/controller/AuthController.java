package com.staybook.controller;

import com.staybook.dto.AuthResponse;
import com.staybook.dto.LoginRequest;
import com.staybook.dto.RegisterRequest;
import com.staybook.entity.Usuario;
import com.staybook.security.JwtService;
import com.staybook.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario register(@Valid @RequestBody RegisterRequest request) {
        return usuarioService.registrar(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.buscarPorUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }
        String token = jwtService.generateToken(usuario.getUsername());
        return new AuthResponse(token, "Bearer", usuario.getUsername());
    }
}
