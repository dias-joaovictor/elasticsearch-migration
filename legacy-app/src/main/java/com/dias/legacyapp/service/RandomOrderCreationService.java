package com.dias.legacyapp.service;

import com.dias.legacyapp.dao.AddressRepository;
import com.dias.legacyapp.dao.LineStatusRepository;
import com.dias.legacyapp.dao.OrderLineRepository;
import com.dias.legacyapp.dao.OrderRepository;
import com.dias.legacyapp.model.Address;
import com.dias.legacyapp.model.LineStatus;
import com.dias.legacyapp.model.Order;
import com.dias.legacyapp.model.OrderLine;
import com.dias.legacyapp.model.Status;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class RandomOrderCreationService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderLineRepository orderLineRepository;
    private final LineStatusRepository LineStatusRepository;
    private final Faker faker;
    private final Random random;

    public Order generateRandomOrder() {
        // Create random Addresses
        Address shipToAddress = generateRandomAddress();
        Address billToAddress = generateRandomAddress();

        // Save Addresses to DB
        addressRepository.save(shipToAddress);
        addressRepository.save(billToAddress);

        // Create Order with random Addresses
        Order order = new Order();
        order.setOrderId(faker.idNumber().valid());
        order.setPlacedDate(getRandomDate());
        order.setTotalAmount(random.nextInt(100) + random.nextDouble());
        order.setShipTo(shipToAddress);
        order.setBillTo(billToAddress);

        // Create random OrderLines and save them to DB
        List<OrderLine> orderLines = generateRandomOrderLines(order);
        orderLineRepository.saveAll(orderLines);

        // Set OrderLines to Order
        order.setOrderLines(orderLines);

        // Save Order to DB
        orderRepository.save(order);

        return order;
    }

    @NotNull
    private LocalDateTime getRandomDate() {
        LocalDateTime startDateTime = LocalDateTime.of(2020, 1, 1, 0, 0); // start datetime
        LocalDateTime endDateTime = LocalDateTime.now(); // end datetime

        long secondsBetween = ChronoUnit.SECONDS.between(startDateTime, endDateTime);
        long randomSeconds = Math.abs(random.nextLong()) % secondsBetween;
        LocalDateTime randomDateTime = startDateTime.plusSeconds(randomSeconds);

        return randomDateTime;
    }

    private Address generateRandomAddress() {
        Address address = new Address();
        address.setFirstName(faker.name().firstName());
        address.setLastName(faker.name().lastName());
        address.setStreet(faker.address().streetAddress());
        address.setNumber(String.valueOf(random.nextInt(100)));
        address.setCountry(faker.address().countryCode());

        return address;
    }

    private List<OrderLine> generateRandomOrderLines(Order order) {
        List<OrderLine> orderLines = new ArrayList<>();
        for (int i = 0; i < random.nextInt(5) + 1; i++) {
            OrderLine orderLine = new OrderLine();
            orderLine.setLineNumber(i + 1);
            orderLine.setEan(faker.code().ean13());
            orderLine.setQuantity(random.nextInt(10));
            orderLine.setPrice(random.nextInt(100) + random.nextDouble());
            orderLine.setOrder(order);

            // Create random LineStatuses and save them to DB
            List<LineStatus> LineStatuses = generateRandomLineStatuses(orderLine);
            LineStatusRepository.saveAll(LineStatuses);

            // Set LineStatuses to OrderLine
            orderLine.setLineStatuses(LineStatuses);

            orderLines.add(orderLine);
        }

        return orderLines;
    }

    private List<LineStatus> generateRandomLineStatuses(OrderLine orderLine) {
        List<LineStatus> LineStatuses = new ArrayList<>();
        for (int i = 0; i < random.nextInt(3) + 1; i++) {
            LineStatus LineStatus = new LineStatus();
            LineStatus.setDate(orderLine.getOrder().getPlacedDate().plusDays(i));
            LineStatus.setStatus(Status.values()[random.nextInt(Status.values().length)]);
            LineStatus.setOrderLine(orderLine);

            LineStatuses.add(LineStatus);
        }

        return LineStatuses;
    }
    
}
