package com.example.api_carteira_digital.repository;

import com.example.api_carteira_digital.dto.UserRequest;
import com.example.api_carteira_digital.entity.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findByCpfCase1() {
        String cpf = "123456789";
        UserRequest userRequest = new UserRequest("João das Neves", "joao@email.com", "123456", cpf);
        this.createUser(userRequest);

        Optional<User> result = this.userRepository.findByCpf(cpf);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User from DB when not exists")
    void findByCpfCase2() {
        String cpf = "123456789";

        Optional<User> result = this.userRepository.findByCpf(cpf);
        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserRequest userRequest) {
        User newUser = new User(userRequest);
        this.entityManager.persist(newUser);

        return newUser;
    }
}