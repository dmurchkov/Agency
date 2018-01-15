package com.dmurchkov.service.agency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ad {
    private long id;
    private Author author;
    private Apartment apartment;
    private String description;
}