package com.example.userserviceapidesign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->auth.requestMatchers("test/test").permitAll());
        http.authorizeHttpRequests(auth->auth.requestMatchers("/test/user").hasAnyRole("USER","ADMIN"));
        http.authorizeHttpRequests(auth->auth.requestMatchers("/test/admin").hasRole("ADMIN"));
        http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        return http.build();

    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user=User.withDefaultPasswordEncoder().
                username("user").
                password("password").
                roles("USER").build();


        UserDetails admin=User.withDefaultPasswordEncoder().
                username("admin").
                password("password").
                roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,admin);

    }


}
