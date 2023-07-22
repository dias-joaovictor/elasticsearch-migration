package com.dias.legacyapp.service;

import com.dias.legacyapp.dao.OrderRepository;
import com.dias.legacyapp.messaging.OrderMigrationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
@Slf4j
public class OrderExporterService {


    private final OrderRepository orderRepository;
    private final OrderMigrationProducer orderMigrationProducer;

    public void exportOrders(int batchSize) {
        orderRepository.findAllByExported(false, Pageable.ofSize(batchSize))
                .stream()
                .filter(Objects::nonNull)
                .forEach(order -> {
                    orderMigrationProducer.produce(order);
                    order.setExported(true);
                    orderRepository.save(order);
                });

    }
}
