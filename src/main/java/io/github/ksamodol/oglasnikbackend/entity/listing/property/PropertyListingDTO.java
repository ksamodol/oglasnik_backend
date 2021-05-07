package io.github.ksamodol.oglasnikbackend.entity.listing.property;

import io.github.ksamodol.oglasnikbackend.entity.listing.ListingDTO;



public class PropertyListingDTO extends ListingDTO{
    private int insideArea;
    private int outsideArea;
    private int floors;
    private int yearBuilt;
    private int numberOfRooms;
    private String propertyType;

    public PropertyListingDTO(Long id, String title, String description, String condition, String timestampCreated, String category, String placeName, String userUsername, int insideArea, int outsideArea, int floors, int yearBuilt, int numberOfRooms, String propertyType) {
        super(id, title, description, condition, timestampCreated, category, placeName, userUsername);
        this.insideArea = insideArea;
        this.outsideArea = outsideArea;
        this.floors = floors;
        this.yearBuilt = yearBuilt;
        this.numberOfRooms = numberOfRooms;
        this.propertyType = propertyType;
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
