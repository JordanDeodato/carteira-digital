package com.example.api_carteira_digital.controller;

import com.example.api_carteira_digital.dto.AccountRequest;
import com.example.api_carteira_digital.dto.AccountResponse;
import com.example.api_carteira_digital.dto.UpdateBalanceResponse;
import com.example.api_carteira_digital.entity.Account;
import com.example.api_carteira_digital.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:5173")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public List<AccountResponse> getAccounts() {
        return accountService.getAllAccounts().stream()
                .map(AccountResponse::new )
                .toList();
    }

    @GetMapping("/{id}")
    public AccountResponse getAccount(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        if (account == null) {
            throw new RuntimeException("Account not found with id: " + id);
        }
        return new AccountResponse(account);
    }

    @PostMapping("")
    public Account createAccount(@RequestBody AccountRequest accountRequest) {
        return accountService.saveAccount(accountRequest);
    }

    @PutMapping("/{id}")
    public UpdateBalanceResponse updateBalance(@PathVariable Long id, @RequestBody AccountRequest accountRequest) {
        Account updatedAccount = accountService.updateBalance(id, accountRequest);
        return new UpdateBalanceResponse(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}
