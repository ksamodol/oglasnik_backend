package io.github.ksamodol.oglasnikbackend.entity.listing;

public class ListingDTO {
    private Long id;
    private String title;
    private String description;
    private String condition;
    private String timestampCreated;
    private String category;
    private String placeName;
    private String userUsername;


    public ListingDTO(Long id, String title, String description, String condition, String timestampCreated, String category, String placeName, String userUsername) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.condition = condition;
        this.timestampCreated = timestampCreated;
        this.category = category;
        this.placeName = placeName;
        this.userUsername = userUsername;
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
