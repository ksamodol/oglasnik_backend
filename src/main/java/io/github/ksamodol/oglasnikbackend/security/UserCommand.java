package io.github.ksamodol.oglasnikbackend.security;

import io.github.ksamodol.oglasnikbackend.entity.listing.Listing;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.Instant;

public class UserCommand {
    @NotBlank
    @Length(min = 3, max = 20, message = "Username can't be shorter than {min} or longer than {max} characters!")
    private String username;
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,128}$", //number,uppercase,lowercase,no whitespace, at least 8 long, at most 128 long
            message = "Password must contain a number, a uppercase letter, a lowercase letter and no whitespace"
    )
    @Length(min = 8, max = 128, message = "Password can't be shorter than {min} or longer than {max} characters!")
    private String password;
    @NotBlank
    @Length(min = 2, max = 32, message = "First name can't be shorter than {min} or longer than {max} characters!")
    private String firstName;
    @NotBlank
    @Length(min = 2, max = 32, message = "Last name can't be shorter than {min} or longer than {max} characters!")
    private String lastName;
    @Email(message = "Must be a valid email address")
    private String email;
    @NotBlank
    private String phoneNumber;

    public UserCommand(String username, String password,String firstName, String lastName, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
