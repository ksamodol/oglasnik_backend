package io.github.ksamodol.oglasnikbackend.entity.listing.vehicle;

import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

public class VehicleListingDTO extends ListingDTO {
    private String make;
    private String model;
    private int year;
    private int mileage;
    private int horsepower;

    public VehicleListingDTO(VehicleListing vehicleListing, List<String> images){
        super(vehicleListing, images);
        this.make = vehicleListing.getMake();
        this.model = vehicleListing.getModel();
        this.year = vehicleListing.getYear();
        this.mileage = vehicleListing.getMileage();
        this.horsepower = vehicleListing.getHorsepower();
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

}
