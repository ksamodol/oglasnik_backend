package io.github.ksamodol.oglasnikbackend.entity.listing;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.location.Place;
import io.github.ksamodol.oglasnikbackend.security.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.Instant;

public class ListingCommand {
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
}
