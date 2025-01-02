package com.example.userserviceapidesign.repo;

import com.example.userserviceapidesign.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserId(Long userId);
    boolean existsByEmail(String email);
    boolean existsByNumber(String number);
}
