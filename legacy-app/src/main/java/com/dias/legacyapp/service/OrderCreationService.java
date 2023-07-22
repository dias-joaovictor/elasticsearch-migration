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

    }

}
