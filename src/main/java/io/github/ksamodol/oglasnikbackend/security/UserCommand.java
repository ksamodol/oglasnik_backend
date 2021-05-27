package io.github.ksamodol.oglasnikbackend.security;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.Instant;

public class UserCommand {
    @NotBlank
    private String username;
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$") //number,uppercase,lowercase,no whitespace, at lease 8 long
    private String password;
    @NotBlank
    private String firstName, lastName;
    @Email
    private String email;

    public UserCommand(@NotBlank String username, @NotBlank String password, @NotBlank String firstName, @NotBlank String lastName, @Email String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
}
