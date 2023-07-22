package com.dias.searchapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document(indexName = "orders")
public class Order {

    @Id
    private String orderId;

    private LocalDateTime placedDate;

    private Double totalAmount;


    private Address shipTo;

    private Address billTo;

    private List<OrderLine> orderLines;

    private boolean exported;
}
