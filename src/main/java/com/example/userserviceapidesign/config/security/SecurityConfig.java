package com.example.userserviceapidesign.config.security;

import com.example.userserviceapidesign.models.entity.authority.Authority;
import com.example.userserviceapidesign.models.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final List<AuthService> authServices;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session->session.sessionCreationPolicy(STATELESS));
        http.authorizeHttpRequests(auth->auth.requestMatchers("/test/test").permitAll());
        http.authorizeHttpRequests(auth->auth.requestMatchers("/user/**").permitAll());
        http.authorizeHttpRequests(auth->auth.requestMatchers("/test/user").hasAnyAuthority(Role.USER.name(),Role.ADMIN.name()));
        http.authorizeHttpRequests(auth->auth.requestMatchers("/test/admin").hasAuthority(Role.ADMIN.name()));
        http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        http.apply(new AuthFilterConfigurerAdapter(authServices));
        return http.build();

    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
