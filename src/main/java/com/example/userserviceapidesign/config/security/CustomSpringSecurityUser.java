package com.example.userserviceapidesign.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomSpringSecurityUser extends User {
@Getter
@Setter
private Long userId;

    public CustomSpringSecurityUser(String username, String password,
                                    Collection<? extends
            GrantedAuthority> authorities, Long userId) {
        super(username, password, authorities);
        this.userId = userId;
    }
}
