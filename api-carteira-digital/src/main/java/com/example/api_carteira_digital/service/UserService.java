package com.example.api_carteira_digital.service;

import com.example.api_carteira_digital.dto.UserRequest;
import com.example.api_carteira_digital.entity.User;
import com.example.api_carteira_digital.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }

    @Transactional
    public User createUser(UserRequest userRequest) {
        User user = convertUser(userRequest);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        BeanUtils.copyProperties(userRequest, user, "id", "createdAt", "updatedAt");
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    private User convertUser(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        return user;
    }
}
