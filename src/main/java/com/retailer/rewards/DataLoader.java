package com.retailer.rewards;

import com.retailer.rewards.model.Transaction;
import com.retailer.rewards.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final TransactionRepository repo;

    public DataLoader(TransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        List<Transaction> seed = List.of(
                new Transaction("C001", "Alice Johnson", 120.00, LocalDate.of(2025, 1, 5)),
                new Transaction("C001", "Alice Johnson", 75.50, LocalDate.of(2025, 1, 15)),
                new Transaction("C001", "Alice Johnson", 200.00, LocalDate.of(2025, 2, 3)),
                new Transaction("C001", "Alice Johnson", 50.00, LocalDate.of(2025, 2, 20)),
                new Transaction("C001", "Alice Johnson", 130.00, LocalDate.of(2025, 3, 10)),
                new Transaction("C001", "Alice Johnson", 45.00, LocalDate.of(2025, 3, 22)),

                new Transaction("C002", "Bob Martinez", 210.00, LocalDate.of(2025, 1, 8)),
                new Transaction("C002", "Bob Martinez", 55.00, LocalDate.of(2025, 1, 25)),
                new Transaction("C002", "Bob Martinez", 90.00, LocalDate.of(2025, 2, 12)),
                new Transaction("C002", "Bob Martinez", 150.00, LocalDate.of(2025, 2, 28)),
                new Transaction("C002", "Bob Martinez", 30.00, LocalDate.of(2025, 3, 5)),
                new Transaction("C002", "Bob Martinez", 180.00, LocalDate.of(2025, 3, 18)),

                new Transaction("C003", "Carol Davis", 65.00, LocalDate.of(2025, 1, 2)),
                new Transaction("C003", "Carol Davis", 110.00, LocalDate.of(2025, 1, 19)),
                new Transaction("C003", "Carol Davis", 300.00, LocalDate.of(2025, 2, 7)),
                new Transaction("C003", "Carol Davis", 40.00, LocalDate.of(2025, 2, 14)),
                new Transaction("C003", "Carol Davis", 95.00, LocalDate.of(2025, 3, 1)),
                new Transaction("C003", "Carol Davis", 170.00, LocalDate.of(2025, 3, 25))
        );

        repo.saveAll(seed);
    }
}
