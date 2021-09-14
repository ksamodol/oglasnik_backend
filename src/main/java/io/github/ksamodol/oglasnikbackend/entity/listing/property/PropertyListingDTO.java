package io.github.ksamodol.oglasnikbackend.entity.listing.property;

import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class PropertyListingDTO extends ListingDTO {
    private int insideArea;
    private int outsideArea;
    private int floors;
    private int yearBuilt;
    private int numberOfRooms;


    public PropertyListingDTO(PropertyListing propertyListing, List<String> images) {
        super(propertyListing, images);
        this.insideArea = propertyListing.getInsideArea();
        this.outsideArea = propertyListing.getOutsideArea();
        this.floors = propertyListing.getFloors();
        this.yearBuilt = propertyListing.getYearBuilt();
        this.numberOfRooms = propertyListing.getNumberOfRooms();
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
    
}
