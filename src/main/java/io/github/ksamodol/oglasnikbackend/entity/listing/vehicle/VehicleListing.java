package io.github.ksamodol.oglasnikbackend.entity.listing.vehicle;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Entity
public class VehicleListing extends Listing {
    private String make;
    private String model;
    private int year;
    private int mileage;
    private int horsepower;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    public VehicleListing() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VehicleListing that = (VehicleListing) o;
        return year == that.year &&
                mileage == that.mileage &&
                horsepower == that.horsepower &&
                Objects.equals(make, that.make) &&
                Objects.equals(model, that.model) &&
                transmission == that.transmission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), make, model, year, mileage, horsepower, transmission);
    }
}
