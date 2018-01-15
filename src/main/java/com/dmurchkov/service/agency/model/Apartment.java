package com.dmurchkov.service.agency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Apartment {
    private double area;
    private int numberOfRooms;
    private long cost;
    private int floor;
    private Address address;
}