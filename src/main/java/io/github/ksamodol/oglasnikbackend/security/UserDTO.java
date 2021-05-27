package io.github.ksamodol.oglasnikbackend.security;

import javax.validation.constraints.NotBlank;

public class UserDTO { //TODO: expand
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public UserDTO(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
