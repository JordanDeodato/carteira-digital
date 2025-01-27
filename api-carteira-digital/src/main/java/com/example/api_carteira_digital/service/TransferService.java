package com.example.api_carteira_digital.service;

import com.example.api_carteira_digital.dto.TransferRequest;
import com.example.api_carteira_digital.entity.Account;
import com.example.api_carteira_digital.entity.Transfer;
import com.example.api_carteira_digital.entity.User;
import com.example.api_carteira_digital.repository.TransferRepository;
import com.example.api_carteira_digital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public List<Transfer> findBySenderAccountId(Long senderAccountId) {
        return transferRepository.findBySenderAccountIdOrderByTransferDateDesc(senderAccountId);
    }

    public Transfer createTransfer(TransferRequest transferRequest) {
        if (transferRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer amount must be greater than zero");
        }

        String cpf = transferRequest.getReceiverCpf();
        Optional<User> receiver = userService.getUserByCpf(cpf);

        User receiverUser = receiver.orElseThrow(() -> new RuntimeException("User not found"));

        Account senderAccount = accountService.getAccountById(transferRequest.getSenderAccountId());
        Account receiverAccount = accountService.getAccountById(receiverUser.getId());

        if (senderAccount.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        senderAccount.setBalance(senderAccount.getBalance().subtract(transferRequest.getAmount()));
        receiverAccount.setBalance(receiverAccount.getBalance().add(transferRequest.getAmount()));

        Transfer transfer = new Transfer();
        transfer.setSenderAccount(senderAccount);
        transfer.setReceiverAccount(receiverAccount);
        transfer.setAmount(transferRequest.getAmount());
        transfer.setTransferDate(LocalDateTime.now());
        return transferRepository.save(transfer);
    }
}
