package com.dias.legacyapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer lineNumber;
    private String ean;
    private Integer quantity;
    private Double price;

    @OneToMany(mappedBy = "orderLine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LineStatus> lineStatuses;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;
}
