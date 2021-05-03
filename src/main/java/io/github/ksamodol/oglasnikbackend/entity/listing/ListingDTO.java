package io.github.ksamodol.oglasnikbackend.entity.listing;
//TODO update dto
public class ListingDTO {
    private Long id;
    private String title;
    private String description;
    private String condition;
    private String placeName;

    public ListingDTO(Long id, String title, String description, String condition, String placeName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.condition = condition;
        this.placeName = placeName;
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
}
