/*
package com.veterinary.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.veterinary.model.Users;
import com.veterinary.repository.UserRepository;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth.requestMatchers("/signin", "/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/roles/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/breeds/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/breeds/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/breeds/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/owners/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/owners/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/owners/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/pets/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/pets/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/pets/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/pet_photos/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/pet_photos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/pet_photos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/species/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/species/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/species/**").hasRole("ADMIN")
                        .requestMatchers("/graphql").authenticated()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults()).csrf(csrf -> csrf.disable())
                .logout(logout -> logout.logoutUrl("/signout").permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Users usuario = userRepository.getUsersByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            String roleName = usuario.getRole().getName();
            if (!roleName.startsWith("ROLE_")) {
                roleName = "ROLE_" + roleName;
            }

            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getPassword())
                    .disabled(usuario.getActive() == null || !usuario.getActive())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority(roleName)))
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
 */
package com.veterinary.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.veterinary.model.Users;
import com.veterinary.repository.UserRepository;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/signin", "/signup").permitAll()
                // ... (tus otras rutas siguen igual, no las borres) ...
                .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable())
                .logout(logout -> logout.logoutUrl("/signout").permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            System.out.println("--- [DEBUG] Intento de login: " + username); // <--- CHIVATO 1

            Users usuario = userRepository.getUsersByEmail(username)
                    .orElseThrow(() -> {
                        System.out.println("--- [DEBUG] ERROR: Usuario no encontrado en BD"); // <--- CHIVATO 2
                        return new UsernameNotFoundException("User not found: " + username);
                    });

            System.out.println("--- [DEBUG] Usuario encontrado. Email: " + usuario.getEmail()); // <--- CHIVATO 3
            System.out.println("--- [DEBUG] Hash en BD: " + usuario.getPassword()); // <--- CHIVATO 4
            System.out.println("--- [DEBUG] Rol: " + usuario.getRole().getName()); // <--- CHIVATO 5

            // PRUEBA MANUAL DE CONTRASEÑA
            boolean coincide = new BCryptPasswordEncoder().matches("admin", usuario.getPassword());
            System.out.println("--- [DEBUG] ¿La contraseña 'admin' coincide con el hash?: " + coincide); // <--- LA PRUEBA DE FUEGO

            String roleName = usuario.getRole().getName();
            if (!roleName.startsWith("ROLE_")) {
                roleName = "ROLE_" + roleName;
            }

            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getPassword())
                    .disabled(usuario.getActive() == null || !usuario.getActive())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority(roleName)))
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
