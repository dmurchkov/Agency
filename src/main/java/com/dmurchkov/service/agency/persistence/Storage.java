package com.dmurchkov.service.agency.persistence;

import com.dmurchkov.service.agency.model.Ad;
import com.dmurchkov.service.agency.model.Address;
import com.dmurchkov.service.agency.model.Apartment;
import com.dmurchkov.service.agency.model.Author;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class Storage {

    private AtomicLong id;
    private final Map<Long, Ad> ads;
    private final Map<Long, Author> authors;
    private final Map<Long, Apartment> apartments;

    public Storage() {
        id = new AtomicLong();
        ads = new HashMap<>();
        authors = new HashMap<>();
        apartments = new HashMap<>();
    }

    public long createAuthor(String name, String email, String phone) {
        long authorId = id.getAndIncrement();
        authors.put(authorId, new Author(authorId, name, email, phone));

        return authorId;
    }

    public Map<Long, Author> allAuthors() {
        return authors;
    }

    public Optional<Author> getAuthorById(long id) {
        return Optional.ofNullable(authors.get(id));
    }

    public long createApartment(double area, int numOfRooms, long cost, int floor,
                                String city, String street, String houseNum) {
        long apartmentId = id.getAndIncrement();
        apartments.put(apartmentId, new Apartment(apartmentId, area, numOfRooms, cost, floor,
                new Address(city, street, houseNum)));

        return apartmentId;
    }

    public Map<Long, Apartment> allApartments() {
        return apartments;
    }

    public Optional<Apartment> getApartmentById(long id) {
        return Optional.ofNullable(apartments.get(id));
    }

    public long submitAdd(long authorId, long apartmentId, String description) {
        long adId = id.getAndIncrement();
        ads.put(adId, new Ad(adId, authorId, apartmentId, description));

        return adId;
    }

    public Map<Long, Ad> allAds() {
        return ads;
    }

    public Optional<Ad> getAdById(long id) {
        return Optional.ofNullable(ads.get(id));
    }
}