package com.example.api_carteira_digital.dto;

import com.example.api_carteira_digital.entity.Account;

import java.math.BigDecimal;

public class UpdateBalanceResponse {
    private Long id;
    private BigDecimal balance;

    public UpdateBalanceResponse(Account account) {
        this.id = account.getId();
        this.balance = account.getBalance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
