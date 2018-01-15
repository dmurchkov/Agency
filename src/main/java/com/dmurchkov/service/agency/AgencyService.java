package com.dmurchkov.service.agency;

import com.dmurchkov.service.agency.exception.NoSuchEntityException;
import com.dmurchkov.service.agency.model.Ad;
import com.dmurchkov.service.agency.model.Apartment;
import com.dmurchkov.service.agency.model.Author;
import com.dmurchkov.service.agency.persistence.Storage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static java.lang.String.valueOf;

@AllArgsConstructor
@Slf4j
public class AgencyService {

    private final Storage storage;

    public long submitAdd(long authorId, long apartmentId, String description) throws NoSuchEntityException {
        if (storage.getAuthorById(authorId) == null) {
            throw new NoSuchEntityException(valueOf(authorId));
        }
        if (storage.getApartmentById(apartmentId) == null) {
            throw new NoSuchEntityException(valueOf(apartmentId));
        }

        return storage.submitAdd(authorId, apartmentId, description);
    }

    public Map<Long, Ad> getAdds() {
        return storage.allAds();
    }

    public Ad getAdById(long id) throws NoSuchEntityException {
        Ad ad = storage.getAdById(id);
        if (ad == null) {
            throw new NoSuchEntityException(valueOf(id));
        }

        return ad;
    }

    public long createAuthor(String name, String email, String phone) {
        return storage.createAuthor(name, email, phone);
    }

    public Author getAuthorById(long id) throws NoSuchEntityException {
        Author author = storage.getAuthorById(id);
        if (author == null) {
            throw new NoSuchEntityException(valueOf(id));
        }

        return author;
    }

    public Map<Long, Author> getAuthors() {
        return storage.allAuthors();
    }

    public long createApartment(double area, int numOfRooms, long cost, int floor,
                                String city, String street, String houseNum) {

        return storage.createApartment(area, numOfRooms, cost, floor, city, street, houseNum);
    }

    public Apartment getApartmentById(long id) throws NoSuchEntityException {
        Apartment apartment = storage.getApartmentById(id);
        if (apartment == null) {
            throw new NoSuchEntityException(valueOf(id));
        }

        return apartment;
    }

    public Map<Long, Apartment> getApartments() {
        return storage.allApartments();
    }
}