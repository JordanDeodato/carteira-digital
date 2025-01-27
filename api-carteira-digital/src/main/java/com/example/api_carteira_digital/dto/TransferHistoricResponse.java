package com.example.api_carteira_digital.dto;

import com.example.api_carteira_digital.entity.Transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferHistoricResponse {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime transferDate;
    private UserTransferReceiverResponse receiver;

    public TransferHistoricResponse(Transfer transfer) {
        this.id = transfer.getId();
        this.amount = transfer.getAmount();
        this.transferDate = transfer.getTransferDate();
        if (transfer.getReceiverAccount() != null && transfer.getReceiverAccount().getUser() != null) {
            this.receiver = new UserTransferReceiverResponse(transfer.getReceiverAccount().getUser());
        } else {
            throw new RuntimeException("Receiver account not found for transfer ID: " + transfer.getId());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public UserTransferReceiverResponse getReceiverAccountId() {
        return receiver;
    }

    public void setReceiverAccountId(UserTransferReceiverResponse receiver) {
        this.receiver = receiver;
    }
}
