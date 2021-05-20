package io.github.ksamodol.oglasnikbackend.entity.listing.property;

import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


public class PropertyListingDTO extends ListingDTO {
    private int insideArea;
    private int outsideArea;
    private int floors;
    private int yearBuilt;
    private int numberOfRooms;
    private String propertyType;

    public PropertyListingDTO(PropertyListing propertyListing) {
        super(propertyListing);
        this.insideArea = propertyListing.getInsideArea();
        this.outsideArea = propertyListing.getOutsideArea();
        this.floors = propertyListing.getFloors();
        this.yearBuilt = propertyListing.getYearBuilt();
        this.numberOfRooms = propertyListing.getNumberOfRooms();
        this.propertyType = propertyListing.getPropertyType().name();
    }

    public int getInsideArea() {
        return insideArea;
    }

    public int getOutsideArea() {
        return outsideArea;
    }

    public int getFloors() {
        return floors;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getPropertyType() {
        return propertyType;
    }
}
