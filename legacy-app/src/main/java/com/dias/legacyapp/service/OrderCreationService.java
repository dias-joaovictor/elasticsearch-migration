package com.dias.legacyapp.service;

import com.dias.legacyapp.dao.OrderRepository;
import com.dias.legacyapp.model.Order;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderCreationService {

    private static final int MAX_ORDERS = 1000;

    private final OrderRepository orderRepository;

    private final Faker faker;

    public void createIfNecessary() {
        while(orderRepository.count() <= MAX_ORDERS) {
            orderRepository.save(Order.builder()
                            .checkoutId(UUID.randomUUID().toString())
                            .name(faker.name().fullName())
                    .build());
        }
    }

}
