package io.github.ksamodol.oglasnikbackend.entity.listing;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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


    public ListingDTO(Long id, String title, String description, String condition, Long price, String timestampCreated, String category, String countyName, String placeName, String userUsername) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.condition = condition;
        this.price = price;
        this.timestampCreated = timestampCreated;
        this.category = category;
        this.countyName = countyName;
        this.placeName = placeName;
        this.userUsername = userUsername;
    }
    public <T extends Listing> ListingDTO(T listing){
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
}
