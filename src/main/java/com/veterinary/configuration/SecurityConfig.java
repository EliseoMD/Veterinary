package com.veterinary.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
                        .requestMatchers(HttpMethod.GET, "/api/v1/appointments/**").hasAnyRole("ADMIN", "USER", "RECEPCIONIST")
                        .requestMatchers(HttpMethod.POST, "/api/v1/appointments/**").hasAnyRole("ADMIN", "USER", "RECEPCIONIST")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/appointments/**").hasAnyRole("USER", "RECEPCIONIST", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/appointment_cancels/**").hasAnyRole("RECEPCIONIST", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/appointment_cancels/**").hasAnyRole("RECEPCIONIST", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/appointment_cancels/**").hasAnyRole("RECEPCIONIST", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/appointment_notes/**").hasAnyRole("VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/appointment_notes/**").hasAnyRole("VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/appointment_notes/**").hasAnyRole("VETERINARIAN", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/appointment_status/**").hasAnyRole("RECEPCIONIST", "USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/appointment_status/**").hasAnyRole("RECEPCIONIST", "USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/appointment_status/**").hasAnyRole("RECEPCIONIST", "USER", "ADMIN")
                        .requestMatchers("/graphql").authenticated()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults()).csrf(csrf -> csrf.disable())
                .logout(logout -> logout.logoutUrl("/signout").permitAll());
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
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
