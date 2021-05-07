package io.github.ksamodol.oglasnikbackend.entity.listing.vehicle;

import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class VehicleListingDTO extends ListingDTO {
    private String make;
    private String model;
    private int year;
    private int mileage;
    private int horsepower;
    private String transmission;

    public VehicleListingDTO(Long id, String title, String description, String condition, String timestampCreated, String category, String placeName, String userUsername, String make, String model, int year, int mileage, int horsepower, String transmission) {
        super(id, title, description, condition, timestampCreated, category, placeName, userUsername);
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.horsepower = horsepower;
        this.transmission = transmission;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getMileage() {
        return mileage;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public String getTransmission() {
        return transmission;
    }
}
