package com.dmurchkov.service.agency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ad {
    private long id;
    private long authorId;
    private long apartmentId;
    private String description;
}