package com.example.api_carteira_digital.service;

import com.example.api_carteira_digital.dto.TransferRequest;
import com.example.api_carteira_digital.dto.UserRequest;
import com.example.api_carteira_digital.entity.Account;
import com.example.api_carteira_digital.entity.Transfer;
import com.example.api_carteira_digital.entity.User;
import com.example.api_carteira_digital.repository.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private UserService userService;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private TransferService transferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createTransfer() {
        UserRequest senderRequest = new UserRequest("João das Neves", "joao@email.com", "123456789", "123456789");
        UserRequest receiverRequest = new UserRequest("Maria das Neves", "maria@email.com", "123456789", "987654321");

        User sender = new User(senderRequest);
        sender.setId(1L);

        User receiver = new User(receiverRequest);
        receiver.setId(2L);

        Account senderAccount = new Account();
        senderAccount.setId(1L);
        senderAccount.setUser(sender);
        senderAccount.setBalance(new BigDecimal("100.00"));

        Account receiverAccount = new Account();
        receiverAccount.setId(2L);
        receiverAccount.setUser(receiver);
        receiverAccount.setBalance(new BigDecimal("50.00"));

        TransferRequest transferRequest = new TransferRequest(sender.getId(), receiverRequest.getCpf(), new BigDecimal(50));

        when(userService.getUserByCpf("987654321")).thenReturn(Optional.of(receiver));
        when(accountService.getAccountById(1L)).thenReturn(senderAccount);
        when(accountService.getAccountById(2L)).thenReturn(receiverAccount);

        transferService.createTransfer(transferRequest);

        verify(userService).getUserByCpf("987654321");
        verify(accountService).getAccountById(1L);
        verify(accountService).getAccountById(2L);

        verify(transferRepository, times(1)).save(any());
    }
}