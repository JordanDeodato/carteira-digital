package com.example.api_carteira_digital.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> sentTransfers;

    @OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transfer> receivedTransfers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public List<Transfer> getSentTransfers() {
        return sentTransfers;
    }

    public void setSentTransfers(List<Transfer> sentTransfers) {
        this.sentTransfers = sentTransfers;
    }

    public List<Transfer> getReceivedTransfers() {
        return receivedTransfers;
    }

    public void setReceivedTransfers(List<Transfer> receivedTransfers) {
        this.receivedTransfers = receivedTransfers;
    }
}
