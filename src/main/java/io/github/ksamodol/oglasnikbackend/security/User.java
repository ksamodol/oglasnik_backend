package io.github.ksamodol.oglasnikbackend.security;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username, password;
    private String firstName, lastName;
    private String email;
    private Instant timestampCreated;
    @OneToMany(mappedBy = "user")
    private List<Listing> listings;

    public User() {
    }

    public User(Long id, String username, String password, String firstName, String lastName, String email, Instant timestampCreated, List<Listing> listings) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.timestampCreated = timestampCreated;
        this.listings = listings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(Instant timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(timestampCreated, user.timestampCreated) &&
                Objects.equals(listings, user.listings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, email, timestampCreated, listings);
    }
}
