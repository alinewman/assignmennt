package com.retailer.rewards.model;

import java.util.Map;

public class RewardsResponse {

    private String customerId;
    private String customerName;
    private Map<String, Integer> pointsPerMonth;
    private int totalPoints;

    public RewardsResponse(String customerId, String customerName, Map<String, Integer> pointsPerMonth, int totalPoints) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.pointsPerMonth = pointsPerMonth;
        this.totalPoints = totalPoints;
    }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Map<String, Integer> getPointsPerMonth() { return pointsPerMonth; }
    public void setPointsPerMonth(Map<String, Integer> pointsPerMonth) { this.pointsPerMonth = pointsPerMonth; }

    public int getTotalPoints() { return totalPoints; }
    public void setTotalPoints(int totalPoints) { this.totalPoints = totalPoints; }
}
