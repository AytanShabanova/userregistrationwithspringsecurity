package com.example.userserviceapidesign.service.impl;

import com.example.userserviceapidesign.mapstruct.UserMapper;
import com.example.userserviceapidesign.models.dto.UserDTO;
import com.example.userserviceapidesign.models.entity.User;
import com.example.userserviceapidesign.repo.UserRepository;
import com.example.userserviceapidesign.service.inter.UserServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInter {


    private final UserRepository userRepository;


    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if ((userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) &&
                (userDTO.getNumber() == null || userDTO.getNumber().isEmpty())) {
            throw new RuntimeException("Either email or number must be provided");
        }

        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty() && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Duplicate email: " + userDTO.getEmail());
        }
        if (userDTO.getNumber() != null && !userDTO.getNumber().isEmpty() && userRepository.existsByNumber(userDTO.getNumber())) {
            throw new RuntimeException("Duplicate number: " + userDTO.getNumber());
        }

        User user = userMapper.toEntity(userDTO);

      User savedUser=  userRepository.save(user);
      return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getUserByUserId(Long userId) {
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User existingUser = userRepository.findUserByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(userDTO.getName());
        existingUser.setSurname(userDTO.getSurname());
        existingUser.setDateOfBirth(userDTO.getDateOfBirth());
        existingUser.setGender(userDTO.getGender());
        existingUser.setCountry(userDTO.getCountry());
        existingUser.setCity(userDTO.getCity());
        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getNumber() != null && !userDTO.getNumber().isEmpty()) {
            existingUser.setNumber(userDTO.getNumber());
        }
        return userMapper.toDTO(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
