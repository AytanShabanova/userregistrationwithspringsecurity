package com.example.userserviceapidesign.models.entity.user;

import com.example.userserviceapidesign.models.entity.authority.Authority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
//    private String number;
//    private String name;
//    private String surname;
//    private LocalDate dateOfBirth;
//    private String gender;
//    private String country;
//    private String city;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authority",joinColumns = @JoinColumn(name = "user_id"))
public List<Authority> authorities;



}
