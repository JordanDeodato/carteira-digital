package com.example.api_carteira_digital.dto;

import com.example.api_carteira_digital.entity.Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {
    private Long id;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AccountUserResponse user;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.balance = account.getBalance();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
        this.user = new AccountUserResponse(account.getUser());
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AccountUserResponse getUser() {
        return user;
    }

    public void setUserId(AccountUserResponse user) {
        this.user = user;
    }
}
