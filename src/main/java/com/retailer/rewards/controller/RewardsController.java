package com.retailer.rewards.controller;

import com.retailer.rewards.model.RewardsResponse;
import com.retailer.rewards.model.Transaction;
import com.retailer.rewards.repository.TransactionRepository;
import com.retailer.rewards.service.RewardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RewardsController {

    private final RewardsService rewardsService;
    private final TransactionRepository transactionRepository;

    public RewardsController(RewardsService rewardsService, TransactionRepository transactionRepository) {
        this.rewardsService = rewardsService;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/rewards")
    public ResponseEntity<List<RewardsResponse>> getAllRewards() {
        return ResponseEntity.ok(rewardsService.getAllRewards());
    }

    @GetMapping("/rewards/{customerId}")
    public ResponseEntity<RewardsResponse> getRewardsByCustomer(@PathVariable String customerId) {
        RewardsResponse resp = rewardsService.getRewardsForCustomer(customerId);
        if (resp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction saved = transactionRepository.save(transaction);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }
}
