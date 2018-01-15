package com.dmurchkov.service.agency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {
    private long id;
    private String name;
    private String email;
    private String phone;
}