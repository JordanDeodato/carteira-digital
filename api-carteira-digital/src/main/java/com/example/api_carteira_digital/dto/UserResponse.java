package com.example.api_carteira_digital.dto;

import com.example.api_carteira_digital.entity.Account;
import com.example.api_carteira_digital.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserAccountResponse account;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.account = user.getAccount() != null ? new UserAccountResponse(user.getAccount()) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserAccountResponse getAccounts() {
        return account;
    }

    public void setAccounts(UserAccountResponse account) {
        this.account = account;
    }
}
