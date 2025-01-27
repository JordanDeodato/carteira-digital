package com.example.api_carteira_digital.service;

import com.example.api_carteira_digital.dto.AccountRequest;
import com.example.api_carteira_digital.entity.Account;
import com.example.api_carteira_digital.entity.User;
import com.example.api_carteira_digital.repository.AccountRepository;
import com.example.api_carteira_digital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        account.getUser().getId();
        return account;
    }

    public Account saveAccount(AccountRequest accountRequest) {
        LocalDateTime now = LocalDateTime.now();
        User user = userRepository.findById(accountRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account();
        account.setBalance(accountRequest.getAmount());
        account.setUpdatedAt(now);
        account.setUser(user);
        return accountRepository.save(account);
    }

    public Account updateBalance(Long id, AccountRequest accountRequest) {
        LocalDateTime now = LocalDateTime.now();
        Account account = getAccountById(id);
        account.setBalance(account.getBalance().add(accountRequest.getAmount()));
        account.setUpdatedAt(now);
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
