package io.github.ksamodol.oglasnikbackend.entity.listing;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.location.Place;


import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
//TODO: timestamp, user, category
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Listing {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private Condition condition;
    private Instant timestampCreated;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "placeId")
    private Place place;

    public Listing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Instant getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(Instant timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return Objects.equals(id, listing.id) &&
                Objects.equals(title, listing.title) &&
                Objects.equals(description, listing.description) &&
                condition == listing.condition &&
                Objects.equals(timestampCreated, listing.timestampCreated) &&
                category == listing.category &&
                Objects.equals(place, listing.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, condition, timestampCreated, category, place);
    }
}
