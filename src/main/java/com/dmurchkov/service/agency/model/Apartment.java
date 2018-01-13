package com.dmurchkov.service.agency.model;

public class Apartment {
    private double area;
    private int numberOfRooms;
    private long cost;
    private int floor;
    private Address address;

    public Apartment() {
    }

    public Apartment(double area, int numberOfRooms, long cost, int floor, Address address) {
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.cost = cost;
        this.floor = floor;
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (Double.compare(apartment.area, area) != 0) return false;
        if (numberOfRooms != apartment.numberOfRooms) return false;
        if (cost != apartment.cost) return false;
        if (floor != apartment.floor) return false;
        return address != null ? address.equals(apartment.address) : apartment.address == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(area);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + numberOfRooms;
        result = 31 * result + (int) (cost ^ (cost >>> 32));
        result = 31 * result + floor;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
