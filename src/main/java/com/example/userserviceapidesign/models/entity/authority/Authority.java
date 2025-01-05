package com.example.userserviceapidesign.models.entity.authority;

import com.example.userserviceapidesign.models.enums.Role;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class Authority implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private Role authority;



    public String getAuthority() {
        return authority.name();
    }
}
