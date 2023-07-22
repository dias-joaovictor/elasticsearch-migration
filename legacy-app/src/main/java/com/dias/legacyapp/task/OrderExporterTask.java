package com.dias.legacyapp.task;

import com.dias.legacyapp.service.OrderCreationService;
import com.dias.legacyapp.service.OrderExporterService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class OrderExporterTask {

    private static final int BATCH_SIZE = 10;

    private final OrderExporterService orderExporterService;

    @Scheduled(cron = "0/5 * * * * ?")
    @Transactional
    public void createOrder() {
        log.info("Exporting orders");
        orderExporterService.exportOrders(BATCH_SIZE);
        log.info("Orders exported");
    }

}
