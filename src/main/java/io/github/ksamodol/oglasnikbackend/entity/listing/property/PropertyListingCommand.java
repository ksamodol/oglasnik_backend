package io.github.ksamodol.oglasnikbackend.entity.listing.property;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.listing.Condition;
import io.github.ksamodol.oglasnikbackend.security.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class PropertyListingCommand {
    @NotBlank(message = "Title can't be blank!")
    @Length(min = 5, max = 200, message = "Title can't be shorter than {min} or longer than {max} characters!")
    private String title;
    @Length(max = 30000, message = "Description can't be longer than {max} characters!")
    private String description;
    private Condition condition;
    @Max(value = 100000000, message = "Price too high!")
    private Long price;
    private Category category;
    @PositiveOrZero(message = "Place has to be a positive number!")
    private Long placeId;
    private User user;
    @PositiveOrZero
    private int insideArea;
    @PositiveOrZero
    private int outsideArea;
    @PositiveOrZero
    private int floors;
    @PositiveOrZero
    private int yearBuilt;
    @PositiveOrZero
    private int numberOfRooms;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Condition getCondition() {
        return condition;
    }

    public Long getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public User getUser() {
        return user;
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
