package com.example.api_carteira_digital.repository;

import com.example.api_carteira_digital.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    public List<Transfer> findBySenderAccountIdOrderByTransferDateDesc(Long senderAccountId);
}
