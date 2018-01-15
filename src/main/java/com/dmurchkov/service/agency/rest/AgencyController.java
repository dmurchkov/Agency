package com.dmurchkov.service.agency.rest;

import com.dmurchkov.service.agency.AgencyService;
import com.dmurchkov.service.agency.exception.NoSuchEntityException;
import com.dmurchkov.service.agency.model.Ad;
import com.dmurchkov.service.agency.model.Apartment;
import com.dmurchkov.service.agency.model.Author;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/agency/")
@AllArgsConstructor
public class AgencyController {

    private final AgencyService agencyService;

    @PostMapping(value = "ad")
    public long submitAdd(
            @RequestParam(value = "authorId") Long authorId,
            @RequestParam(value = "apartmentId") Long apartmentId,
            @RequestParam(value = "description", required = false) String description) throws NoSuchEntityException {

        return agencyService.submitAdd(authorId, apartmentId, description);
    }

    @GetMapping(value = "ad/{id}")
    public Ad getAddById(@PathVariable("id") long id) throws NoSuchEntityException {

        return agencyService.getAdById(id);
    }

    @GetMapping(value = "ads")
    public Map<Long, Ad> getAdds() {

        return agencyService.getAdds();
    }

    @PostMapping(value = "author")
    public long createAuthor(@RequestParam(value = "name") String name,
                             @RequestParam(value = "email") String email,
                             @RequestParam(value = "phone") String phone) {

        return agencyService.createAuthor(name, email, phone);
    }

    @GetMapping(value = "author/{id}")
    public Author getAuthorById(@PathVariable("id") long id) throws NoSuchEntityException {

        return agencyService.getAuthorById(id);
    }

    @GetMapping(value = "authors")
    public Map<Long, Author> getAuthors() {
        return agencyService.getAuthors();
    }

    @PostMapping(value = "apartment")
    public long createApartment(@RequestParam(value = "area") Double area,
                                @RequestParam(value = "numOfRooms") Integer numOfRooms,
                                @RequestParam(value = "cost") Long cost,
                                @RequestParam(value = "floor") Integer floor,
                                @RequestParam(value = "city") String city,
                                @RequestParam(value = "street") String street,
                                @RequestParam(value = "houseNum") String houseNum) {

        return agencyService.createApartment(area, numOfRooms, cost, floor, city, street, houseNum);
    }

    @GetMapping(value = "apartment/{id}")
    public Apartment getApartmentById(@PathVariable("id") long id) throws NoSuchEntityException {
        return agencyService.getApartmentById(id);
    }

    @GetMapping(value = "apartments")
    public Map<Long, Apartment> getApartments() {
        return agencyService.getApartments();
    }
}