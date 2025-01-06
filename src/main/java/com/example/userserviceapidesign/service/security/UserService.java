package com.example.userserviceapidesign.service.security;

import com.example.userserviceapidesign.mapstruct.UserMapper;
import com.example.userserviceapidesign.models.dto.UserDTO;
import com.example.userserviceapidesign.models.entity.authority.Authority;
import com.example.userserviceapidesign.models.entity.user.User;
import com.example.userserviceapidesign.models.enums.Role;
import com.example.userserviceapidesign.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.example.userserviceapidesign.models.enums.Role.USER;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void createUser(UserDTO userDTO){
        userRepository.findByUsername(userDTO.getUsername()).ifPresentOrElse(
                user -> {
                    throw new NoSuchElementException("User with username " + userDTO.getUsername() + " already exists");

                },
                ()->{
                  User user=  User.builder()
                            .username(userDTO.getUsername())
                            .password(passwordEncoder.encode(userDTO.getPassword()))
                                    .authorities(List.of(Authority
                                            .builder()
                                            .authority(USER).build()))
                            .build();
                    userRepository.save(user);
                }
        );

    }

}
