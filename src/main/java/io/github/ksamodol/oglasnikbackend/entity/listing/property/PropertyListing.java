package io.github.ksamodol.oglasnikbackend.entity.listing.property;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Entity
public class PropertyListing extends Listing {
    private int insideArea;
    private int outsideArea;
    private int floors;
    private int yearBuilt;
    private int numberOfRooms;
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    public PropertyListing() {
    }

    public int getInsideArea() {
        return insideArea;
    }

    public void setInsideArea(int insideArea) {
        this.insideArea = insideArea;
    }

    public int getOutsideArea() {
        return outsideArea;
    }

    public void setOutsideArea(int outsideArea) {
        this.outsideArea = outsideArea;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PropertyListing that = (PropertyListing) o;
        return insideArea == that.insideArea &&
                outsideArea == that.outsideArea &&
                floors == that.floors &&
                yearBuilt == that.yearBuilt &&
                numberOfRooms == that.numberOfRooms &&
                propertyType == that.propertyType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), insideArea, outsideArea, floors, yearBuilt, numberOfRooms, propertyType);
    }
}
