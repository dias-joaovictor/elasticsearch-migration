package com.dias.searchapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private Integer id;
    private String firstName;
    private String lastName;
    private String street;
    private String number;
    private String country;
}