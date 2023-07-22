package com.dias.legacyapp.task;

import com.dias.legacyapp.service.OrderCreationService;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreationTask {

    private final OrderCreationService orderCreationService;

    @Scheduled(cron = "0/30 * * * * ?")
    public void createOrder() {
        orderCreationService.createIfNecessary();
    }

}
