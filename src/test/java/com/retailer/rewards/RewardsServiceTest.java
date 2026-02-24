package com.retailer.rewards;

import com.retailer.rewards.service.RewardsService;
import com.retailer.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RewardsServiceTest {

    private final TransactionRepository mockRepo = Mockito.mock(TransactionRepository.class);
    private final RewardsService service = new RewardsService(mockRepo);

    @Test
    void zeroPointsUnder50() {
        assertEquals(0, service.calculatePoints(49.99));
        assertEquals(0, service.calculatePoints(25.00));
        assertEquals(0, service.calculatePoints(0));
    }

    @Test
    void pointsBetween50And100() {
        assertEquals(0, service.calculatePoints(50.00));
        assertEquals(25, service.calculatePoints(75.00));
        assertEquals(50, service.calculatePoints(100.00));
    }

    @Test
    void pointsOver100() {
        assertEquals(90, service.calculatePoints(120.00));
        assertEquals(250, service.calculatePoints(200.00));
        assertEquals(150, service.calculatePoints(150.00));
    }

    @Test
    void unknownCustomerReturnsNull() {
        Mockito.when(mockRepo.findByCustomerId("UNKNOWN")).thenReturn(Collections.emptyList());
        assertNull(service.getRewardsForCustomer("UNKNOWN"));
    }
}
