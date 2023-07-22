package com.dias.legacyapp.service;

import com.dias.legacyapp.dao.OrderRepository;
import com.dias.legacyapp.model.Order;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreationService {

    private static final int MAX_ORDERS = 20000;

    private final OrderRepository orderRepository;
    private final RandomOrderCreationService randomOrderCreationService;

    public void createIfNecessary() {
        var itemsToCreate = MAX_ORDERS - orderRepository.count();
        log.info("Is necessary to create more registries? {}", itemsToCreate > 0);
        if (itemsToCreate > 0) {
            log.info("Creating {} orders", itemsToCreate);
            for (int i = 0; i < itemsToCreate; i++) {
                Order order = randomOrderCreationService.generateRandomOrder();
                orderRepository.save(order);
            }
        }
    }

}
