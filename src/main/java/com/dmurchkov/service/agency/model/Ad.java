package com.dmurchkov.service.agency.model;


public class Ad {
    private long id;
    private Author author;
    private Apartment apartment;
    private String description;

    public Ad() {
    }

    public Ad(long id, Author author, Apartment apartment, String description) {
        this.id = id;
        this.author = author;
        this.apartment = apartment;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ad ad = (Ad) o;

        if (id != ad.id) return false;
        if (author != null ? !author.equals(ad.author) : ad.author != null) return false;
        if (apartment != null ? !apartment.equals(ad.apartment) : ad.apartment != null) return false;
        return description != null ? description.equals(ad.description) : ad.description == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
