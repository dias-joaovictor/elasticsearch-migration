package com.dias.searchapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLine {

    private Integer id;
    private Integer lineNumber;
    private String ean;
    private Integer quantity;
    private Double price;

    private List<LineStatus> lineStatuses;
}
