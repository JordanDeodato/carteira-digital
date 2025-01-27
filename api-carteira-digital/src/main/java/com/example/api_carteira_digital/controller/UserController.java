package com.example.api_carteira_digital.controller;

import com.example.api_carteira_digital.dto.AccountResponse;
import com.example.api_carteira_digital.dto.UserRequest;
import com.example.api_carteira_digital.dto.UserResponse;
import com.example.api_carteira_digital.entity.User;
import com.example.api_carteira_digital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(UserResponse::new)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    @PostMapping("")
    public User createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
