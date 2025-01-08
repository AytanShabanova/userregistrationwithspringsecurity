package com.example.userserviceapidesign.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtAuthService implements AuthService {
    private final BaseJwtService baseJwtService;


    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try{
                Jws<Claims>claimsJws= baseJwtService.parse(token);
                return Optional.of(getAuthentication(claimsJws.getPayload()));
            }catch (JwtException exception){
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
    private Authentication getAuthentication(Claims claims){
        List<String> roles = (List)claims.get("authority");
        List<GrantedAuthority>authorityList;
        authorityList=roles
                .stream()
                .map(role->new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
//        var details=new CustomSpringSecurityUser(claims.getSubject(), "",
//        authorityList,claims.get("id",Long.class));

        JwtCredentials jwtCredentials=new ModelMapper().map(claims, JwtCredentials.class);

return new UsernamePasswordAuthenticationToken(null,jwtCredentials,authorityList);
    }
}
