package com.example.userserviceapidesign.repo;

import com.example.userserviceapidesign.models.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long userId);
    boolean existsByUsername(String email);
//    boolean existsByNumber(String number);
    Optional<User>findByUsername(String username);
}
