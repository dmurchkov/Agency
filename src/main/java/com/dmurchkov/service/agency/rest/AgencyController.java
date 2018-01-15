package com.dmurchkov.service.agency.rest;

import com.dmurchkov.service.agency.AgencyService;
import com.dmurchkov.service.agency.exception.NoSuchAdException;
import com.dmurchkov.service.agency.model.Ad;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/agency/ads")
@AllArgsConstructor
public class AgencyController {

    private final AgencyService agencyService;

    @PostMapping(value = "/submit")
    public long submit(@RequestParam(value = "name") String name,
                       @RequestParam(value = "email") String email,
                       @RequestParam(value = "phone") String phone,
                       @RequestParam(value = "area") Double area,
                       @RequestParam(value = "numOfRooms") Integer numOfRooms,
                       @RequestParam(value = "cost") Long cost,
                       @RequestParam(value = "floor") Integer floor,
                       @RequestParam(value = "city") String city,
                       @RequestParam(value = "street") String street,
                       @RequestParam(value = "houseNum") String houseNum,
                       @RequestParam(value = "description", required = false) String description) {

        return agencyService.submit(name, email, phone, area, numOfRooms, cost, floor, city, street, houseNum, description);
    }

    @GetMapping(value = "/")
    public Map<Long, Ad> getAll() {
        return agencyService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Ad getById(@PathVariable("id") long id) throws NoSuchAdException {
        return agencyService.getById(id);
    }
}