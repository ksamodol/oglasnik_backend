package io.github.ksamodol.oglasnikbackend.entity.listing;

import io.github.ksamodol.oglasnikbackend.entity.location.Place;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Listing {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private Condition condition;
    @ManyToOne
    @JoinColumn(name = "placeId")
    private Place place;

    public Listing() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return id == listing.id &&
                Objects.equals(title, listing.title) &&
                Objects.equals(description, listing.description) &&
                condition == listing.condition &&
                Objects.equals(place, listing.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, condition, place);
    }
}
