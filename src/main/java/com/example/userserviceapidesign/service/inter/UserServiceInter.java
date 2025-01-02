package com.example.userserviceapidesign.service.inter;

import com.example.userserviceapidesign.models.dto.UserDTO;

public interface UserServiceInter {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserByUserId(Long userId);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);
}
