package com.dias.searchapp.dao;

import com.dias.searchapp.model.Order;
import com.dias.searchapp.model.PartialView;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends ElasticsearchRepository<Order, String> {
    List<PartialView> findByOrderId(String orderId);
}
