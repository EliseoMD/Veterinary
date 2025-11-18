package com.veterinary.configuration;

import java.util.Collections;
import java.util.List;

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

import com.veterinary.model.SystemUser;
import com.veterinary.repository.SystemUserRepository;

@Configuration
public class SecurityConfig {

    private final SystemUserRepository userRepository;

    public SecurityConfig(SystemUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth.requestMatchers("/signin", "/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/system_users/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/system_users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/system_users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/system_roles/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/system_roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/system_roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/tretments/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/tretments/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/tretments/**").hasRole("ADMIN")
                        .requestMatchers("/graphql").authenticated()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults()).csrf(csrf -> csrf.disable())
                //.formLogin(withDefaults())
                //.rememberMe(withDefaults())
                .logout(logout -> logout.logoutUrl("/signout").permitAll());
        return http.build();
    }

    //Se cambia para que se conecte a la base de datos
    //Cambiar un InMemory por un JPA
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            List<SystemUser> usuarios = userRepository.getSystemUsersByEmail(username);
            if (usuarios.isEmpty()) {
                throw new UsernameNotFoundException("User not found: " + username);
            }
            SystemUser usuario = usuarios.get(0);
            String roleName = usuario.getRole().getName();
            if (!roleName.startsWith("ROLE_")) {
                roleName = "ROLE_" + roleName;
            }
            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getPassword())
                    .disabled(!usuario.getActive())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority(roleName)))
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
