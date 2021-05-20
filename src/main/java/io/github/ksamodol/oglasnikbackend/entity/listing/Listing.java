package io.github.ksamodol.oglasnikbackend.entity.listing;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.location.Place;
import io.github.ksamodol.oglasnikbackend.security.User;


import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
//TODO: price
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Listing{
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Condition condition;
    private Long price; //in lipas
    private Instant timestampCreated;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "placeId")
    private Place place;

    @ManyToOne
    private User user;

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                Objects.equals(price, listing.price) &&
                Objects.equals(timestampCreated, listing.timestampCreated) &&
                category == listing.category &&
                Objects.equals(place, listing.place) &&
                Objects.equals(user, listing.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, condition, price, timestampCreated, category, place, user);
    }
}
