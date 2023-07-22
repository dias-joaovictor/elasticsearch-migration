package com.dias.searchapp.dao;

import com.dias.searchapp.model.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends ElasticsearchRepository<Order, String> {
    List<Order> findByOrderId(String orderId);

    List<Order> findByBillToLastName(String billToLastName);

    List<Order> findByBillToFirstNameAndBillToLastName(String billToFirstName, String billToLastName);
}
