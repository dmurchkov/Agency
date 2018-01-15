package com.dmurchkov.service.agency;

import com.dmurchkov.service.agency.model.Ad;
import com.dmurchkov.service.agency.model.Address;
import com.dmurchkov.service.agency.model.Apartment;
import com.dmurchkov.service.agency.model.Author;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Storage {

    private AtomicLong adId;
    private final Map<Long, Ad> ads;

    public Storage() {
        ads = new HashMap<>();
        adId = new AtomicLong();
    }

    public long createAd(String name, String email, String phone, Double area, Integer numOfRooms, Long cost,
                         Integer floor, String city, String street, String houseNum, String description) {
        long id = adId.getAndIncrement();
        ads.put(id, new Ad(id,
                new Author(name, email, phone),
                new Apartment(area, numOfRooms, cost, floor, new Address(city, street, houseNum)),
                description));

        return id;
    }

    public Map<Long, Ad> allAds() {
        return ads;
    }

    public Ad getById(long id) {
        return ads.get(id);
    }
}