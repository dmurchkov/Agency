package com.dmurchkov.service.agency;

import com.dmurchkov.service.agency.exception.NoSuchAdException;
import com.dmurchkov.service.agency.model.Ad;
import lombok.AllArgsConstructor;

import java.util.Map;

import static java.lang.String.valueOf;

@AllArgsConstructor
public class AgencyService {

    private final Storage storage;

    public long submit(String name, String email, String phone, Double area, Integer numOfRooms, Long cost,
                       Integer floor, String city, String street, String houseNum, String description) {
        return storage.createAd(name, email, phone, area, numOfRooms, cost, floor, city, street, houseNum, description);
    }

    public Map<Long, Ad> getAll() {
        return storage.allAds();
    }

    public Ad getById(long id) {
        Ad adById = storage.getById(id);
        if (adById == null) {
            throw new NoSuchAdException("id:" + valueOf(id));
        }
        return adById;
    }
}