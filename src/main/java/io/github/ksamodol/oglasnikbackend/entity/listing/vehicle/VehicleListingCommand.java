package io.github.ksamodol.oglasnikbackend.entity.listing.vehicle;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.listing.Condition;
import io.github.ksamodol.oglasnikbackend.security.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class VehicleListingCommand {
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
    @Length(max = 30, message = "Make can't be longer than {max} characters!")
    private String make;
    @Length(max = 30, message = "Model can't be longer than {max} characters!")
    private String model;
    @PositiveOrZero
    private int year;
    @PositiveOrZero
    private int mileage;
    @PositiveOrZero
    private int horsepower;

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

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getMileage() {
        return mileage;
    }

    public int getHorsepower() {
        return horsepower;
    }
}
