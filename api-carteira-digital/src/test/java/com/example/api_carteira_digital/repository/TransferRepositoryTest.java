package com.example.api_carteira_digital.repository;

import com.example.api_carteira_digital.dto.TransferRequest;
import com.example.api_carteira_digital.dto.UserRequest;
import com.example.api_carteira_digital.entity.Account;
import com.example.api_carteira_digital.entity.Transfer;
import com.example.api_carteira_digital.entity.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TransferRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Should return a list of transfers by SenderAccountId ordered by TransferDate descending")
    void findBySenderAccountIdOrderByTransferDateDesc() {
        String cpf = "123456789";
        UserRequest userRequest = new UserRequest("João das Neves", "joao@email.com", "123456", cpf);
        User senderUser = createUser(userRequest);

        Account senderAccount = createAccount(senderUser);

        User receiverUser = createUser(new UserRequest("Maria das Neves", "maria@email.com", "654321", "987654321"));
        Account receiverAccount = createAccount(receiverUser);

        TransferRequest transferRequest = new TransferRequest(senderAccount.getId(), receiverUser.getCpf(), BigDecimal.valueOf(100.00));
        createTransfer(transferRequest, senderAccount, receiverAccount);

        List<Transfer> result = transferRepository.findBySenderAccountIdOrderByTransferDateDesc(senderAccount.getId());

        assertNotNull(result);
        assertNotNull(result.get(0).getTransferDate());
    }

    private User createUser(UserRequest userRequest) {
        User newUser = new User(userRequest);
        entityManager.persist(newUser);
        return newUser;
    }

    private Account createAccount(User user) {
        Account account = new Account();
        account.setUser(user);
        account.setBalance(BigDecimal.valueOf(1000.00));
        account.setCreatedAt(LocalDateTime.now());
        entityManager.persist(account);
        return account;
    }

    private Transfer createTransfer(TransferRequest transferRequest, Account senderAccount, Account receiverAccount) {
        Transfer transfer = new Transfer();
        transfer.setSenderAccount(senderAccount);
        transfer.setReceiverAccount(receiverAccount);
        transfer.setAmount(transferRequest.getAmount());
        transfer.setTransferDate(LocalDateTime.now());
        entityManager.persist(transfer);
        return transfer;
    }
}
