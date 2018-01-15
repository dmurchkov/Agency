package com.dmurchkov.service.agency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    private String city;
    private String street;
    private String houseNumber;
}