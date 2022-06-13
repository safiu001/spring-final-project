package com.sd.springfinalproject.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserModel {
    @NotNull(message="Cannot be Empty")
    @NotEmpty(message="Cannot be Empty")
    private String username;

    @NotNull(message="Cannot be Empty")
    @NotEmpty(message="Cannot be Empty")
    private String firstName;

    private String lastName;

    @NotNull(message="Cannot be Empty")
    @NotEmpty(message="Cannot be Empty")
    private String password;

    public UserModel() {
    }

    public UserModel(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
