package com.retailer.rewards.service;

import com.retailer.rewards.model.RewardsResponse;
import com.retailer.rewards.model.Transaction;
import com.retailer.rewards.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardsService {

    private final TransactionRepository transactionRepository;

    public RewardsService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public int calculatePoints(double amount) {
        int pts = 0;
        int dollars = (int) amount;

        if (dollars > 100) {
            pts += (dollars - 100) * 2;
            pts += 50;
        } else if (dollars > 50) {
            pts += (dollars - 50);
        }

        return pts;
    }

    public RewardsResponse getRewardsForCustomer(String customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

        if (transactions.isEmpty()) {
            return null;
        }

        String name = transactions.get(0).getCustomerName();

        Map<String, Integer> monthlyPoints = new LinkedHashMap<>();

        Map<String, List<Transaction>> grouped = transactions.stream()
                .collect(Collectors.groupingBy(t -> {
                    String month = t.getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.US);
                    int year = t.getDate().getYear();
                    return month + " " + year;
                }));

        int total = 0;

        for (Map.Entry<String, List<Transaction>> entry : grouped.entrySet()) {
            int monthTotal = 0;
            for (Transaction t : entry.getValue()) {
                monthTotal += calculatePoints(t.getAmount());
            }
            monthlyPoints.put(entry.getKey(), monthTotal);
            total += monthTotal;
        }

        return new RewardsResponse(customerId, name, monthlyPoints, total);
    }

    public List<RewardsResponse> getAllRewards() {
        List<Transaction> all = transactionRepository.findAll();

        Map<String, List<Transaction>> byCustomer = all.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId));

        List<RewardsResponse> results = new ArrayList<>();

        for (Map.Entry<String, List<Transaction>> entry : byCustomer.entrySet()) {
            String custId = entry.getKey();
            List<Transaction> transactions = entry.getValue();
            String name = transactions.get(0).getCustomerName();

            Map<String, Integer> monthlyPoints = new LinkedHashMap<>();
            Map<String, List<Transaction>> grouped = transactions.stream()
                    .collect(Collectors.groupingBy(t -> {
                        String month = t.getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.US);
                        int year = t.getDate().getYear();
                        return month + " " + year;
                    }));

            int total = 0;
            for (Map.Entry<String, List<Transaction>> monthEntry : grouped.entrySet()) {
                int monthTotal = 0;
                for (Transaction t : monthEntry.getValue()) {
                    monthTotal += calculatePoints(t.getAmount());
                }
                monthlyPoints.put(monthEntry.getKey(), monthTotal);
                total += monthTotal;
            }

            results.add(new RewardsResponse(custId, name, monthlyPoints, total));
        }

        return results;
    }
}
