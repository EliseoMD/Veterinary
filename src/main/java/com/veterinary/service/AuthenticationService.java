package com.veterinary.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.veterinary.dto.request.LoginRequest;
import com.veterinary.dto.response.LoginResponse;
import com.veterinary.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService; // Tu clase que genera tokens
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request) {
        // 1. Esto valida usuario y contraseña automáticamente. 
        // Si falla, lanza una excepción y devuelve 403/401.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Si pasamos el paso 1, el usuario es correcto. Lo buscamos.
        var user = userRepository.getUsersByEmail(request.getEmail())
                .orElseThrow();

        // 3. Generamos el token
        var jwtToken = jwtService.generateToken(user);

        // 4. Devolvemos la respuesta
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }
}
