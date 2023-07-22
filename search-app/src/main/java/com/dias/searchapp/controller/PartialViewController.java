package com.dias.searchapp.controller;


import com.dias.searchapp.dao.OrderRepository;
import com.dias.searchapp.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class PartialViewController {

    private final OrderRepository orderRepository;
    @GetMapping("{id}")
    public List<Order> getOrder(@PathVariable("id") String orderId) {
        return orderRepository.findByOrderId(orderId);
    }


    @GetMapping("/lastname/{lastname}")
    public List<Order> getOrderByLastName(@PathVariable("lastname") String lastName) {
        return orderRepository.findByBillToLastName(lastName);
    }

    @GetMapping("/name/{firstname}/{lastname}")
    public List<Order> getOrderByLastNameAndPlacedDate(@PathVariable("firstname") String firstName, @PathVariable("lastname") String lastName) {
        return orderRepository.findByBillToFirstNameAndBillToLastName(firstName, lastName);
    }
}
