package com.dmurchkov.service.agency;

import com.dmurchkov.service.agency.model.Ad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class AgencyService {

    @Autowired
    private Storage storage;

    public long submit(String name, String email, String phone, Double area, Integer numOfRooms, Long cost,
                       Integer floor, String city, String street, String houseNum, String description) {
        return storage.createAd(name, email, phone, area, numOfRooms, cost, floor, city, street, houseNum, description);
    }

    public Map<Long, Ad> getAll() {
        return storage.allAds();
    }

    public Ad getById(long id) {
        return storage.getById(id);
    }
}
