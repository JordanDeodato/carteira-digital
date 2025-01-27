package com.example.api_carteira_digital.dto;

import java.math.BigDecimal;

public class TransferRequest {
    private Long senderAccountId;
    private String receiverCpf;
    private BigDecimal amount;

    public TransferRequest(Long senderAccountId, String receiverCpf, BigDecimal amount) {
        this.senderAccountId = senderAccountId;
        this.receiverCpf = receiverCpf;
        this.amount = amount;
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public String getReceiverCpf() {
        return receiverCpf;
    }

    public void setReceiverCpf(String receiverCpf) {
        this.receiverCpf = receiverCpf;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
