package com.example.api_carteira_digital.controller;

import com.example.api_carteira_digital.dto.TransferHistoricResponse;
import com.example.api_carteira_digital.dto.TransferRequest;
import com.example.api_carteira_digital.entity.Transfer;
import com.example.api_carteira_digital.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping("")
    public List<Transfer> getAllTransfers() {
        return transferService.getAllTransfers();
    }

    @GetMapping("/{id}")
    public List<TransferHistoricResponse> findBySenderAccountId(@PathVariable Long id) {
        return transferService.findBySenderAccountId(id).stream()
                .map(TransferHistoricResponse::new)
                .toList();
    }

    @PostMapping("")
    public ResponseEntity<Transfer> createTransfer(@RequestBody TransferRequest transferRequest) {
        Transfer transfer = transferService.createTransfer(transferRequest);
        URI location =    ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transfer.getId())
                .toUri();

        return ResponseEntity.created(location).body(transfer);
    }
}
