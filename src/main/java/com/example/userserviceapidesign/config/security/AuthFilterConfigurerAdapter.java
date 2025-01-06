package com.example.userserviceapidesign.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class AuthFilterConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
   private final List<AuthService>authServices;

    @Override
    public void configure(HttpSecurity http) throws Exception {


        http.addFilterBefore(new AuthRequestFilter(authServices), UsernamePasswordAuthenticationFilter.class);
    }
}
