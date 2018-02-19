package com.dmurchkov.service.agency;

import com.dmurchkov.service.agency.exception.NoSuchEntityException;
import com.dmurchkov.service.agency.kafka.AgencyKafkaProducer;
import com.dmurchkov.service.agency.model.Ad;
import com.dmurchkov.service.agency.model.Apartment;
import com.dmurchkov.service.agency.model.Author;
import com.dmurchkov.service.agency.persistence.Storage;
import lombok.AllArgsConstructor;

import java.util.Map;

import static java.lang.String.format;
import static java.lang.String.valueOf;

@AllArgsConstructor
public class AgencyService {

    private final Storage storage;
    private final AgencyKafkaProducer agencyKafkaProducer;

    @SuppressWarnings("all")
    public long submitAdd(long authorId, long apartmentId, String description) throws NoSuchEntityException {
        storage.getAuthorById(authorId).orElseThrow(() ->
                new NoSuchEntityException("Author id: " + valueOf(authorId)));
        storage.getApartmentById(apartmentId).orElseThrow(() ->
                new NoSuchEntityException("Apartment id: " + valueOf(apartmentId)));

        return storage.submitAdd(authorId, apartmentId, description);
    }

    public Map<Long, Ad> getAdds() {
        return storage.allAds();
    }

    @SuppressWarnings("all")
    public Ad getAdById(long id) throws NoSuchEntityException {
        storage.getAdById(id).orElseThrow(() -> new NoSuchEntityException(valueOf(id)));

        return storage.getAdById(id).get();
    }

    public long createAuthor(String name, String email, String phone) {
        long authorId = storage.createAuthor(name, email, phone);
        String kafkaMessage = format("Author has been created successfully, id:%s", authorId);
        agencyKafkaProducer.send(kafkaMessage);

        return authorId;
    }

    @SuppressWarnings("all")
    public Author getAuthorById(long id) throws NoSuchEntityException {
        storage.getAuthorById(id).orElseThrow(() -> new NoSuchEntityException(valueOf(id)));

        return storage.getAuthorById(id).get();
    }

    public Map<Long, Author> getAuthors() {
        return storage.allAuthors();
    }

    public long createApartment(double area, int numOfRooms, long cost, int floor,
                                String city, String street, String houseNum) {

        return storage.createApartment(area, numOfRooms, cost, floor, city, street, houseNum);
    }

    @SuppressWarnings("all")
    public Apartment getApartmentById(long id) throws NoSuchEntityException {
        storage.getApartmentById(id).orElseThrow(() -> new NoSuchEntityException(valueOf(id)));

        return storage.getApartmentById(id).get();
    }

    public Map<Long, Apartment> getApartments() {
        return storage.allApartments();
    }
}