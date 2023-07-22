package com.dias.legacyapp.messaging;

import com.dias.legacyapp.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderMigrationProducer {

    private final StreamBridge streamBridge;

    public void produce(Order order) {
        var sent = streamBridge.send("migratedOrders-out-0", order);
        if(sent) {
            log.info("Order {} sent to migration", order.getOrderId());
        }else {
            log.error("Error sending order {} to migration", order.getOrderId());
        }
    }

}
