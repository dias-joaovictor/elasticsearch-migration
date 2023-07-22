package com.dias.legacyapp.service;

import com.dias.legacyapp.dao.OrderRepository;
import com.dias.legacyapp.model.Order;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreationService {

    private static final int MAX_ORDERS = 10000;

    private final OrderRepository orderRepository;

    private final Faker faker;

    public void createIfNecessary() {
        var shouldCreate = orderRepository.count() <= MAX_ORDERS;
        if (shouldCreate) {
            log.info("Creating orders");
            while (orderRepository.count() <= MAX_ORDERS) {
                orderRepository.save(Order.builder()
                        .checkoutId(UUID.randomUUID().toString())
                        .name(faker.name().fullName())
                        .build());
            }
            log.info("Creating orders has finished");
        } else {
            updateOrders();
        }

    }

    public void updateOrders() {
        log.info("Updating orders");
        orderRepository.findAllByUpdated(false)
                .forEach(order -> {
                    order.setName(order.getName() + "- updated");
                    order.setUpdated(true);
                    orderRepository.save(order);
                });
        log.info("Updating has finished");
    }

}
