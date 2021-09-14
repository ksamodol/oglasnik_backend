package io.github.ksamodol.oglasnikbackend.entity.listing;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListingDTO {
    private Long id;
    private String title;
    private String description;
    private String condition;
    private Long price;
    private String timestampCreated;
    private String category;
    private String countyName;
    private String placeName;
    private String userUsername;
    private String userPhoneNumber;
    private List<String> images;


    public <T extends Listing> ListingDTO(T listing, List<String> images){
        this.id = listing.getId();
        this.title = listing.getTitle();
        this.description = listing.getDescription();
        this.condition = listing.getCondition().name();
        this.price = listing.getPrice();
        this.timestampCreated = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.from(ZoneOffset.UTC)).format(listing.getTimestampCreated());
        this.category = listing.getCategory().name();
        this.countyName = listing.getPlace().getCounty().getName();
        this.placeName = listing.getPlace().getName();
        this.userUsername = listing.getUser().getUsername();
        this.userPhoneNumber = listing.getUser().getPhoneNumber();
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCondition() {
        return condition;
    }

    public Long getPrice() {
        return price;
    }

    public String getCountyName() { return countyName; }

    public String getPlaceName() {
        return placeName;
    }

    public String getTimestampCreated() {
        return timestampCreated;
    }

    public String getCategory() {
        return category;
    }
    public String getUserUsername(){
        return userUsername;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public List<String> getImages() {
        return images;
    }
}
