package com.sd.springfinalproject.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MovieDto {
    @NotNull(message="Name cannot be Null")
    @NotEmpty(message="Name cannot be Empty")
    private String name;

    private int id;

    @NotNull(message="Description cannot be Empty")
    @NotEmpty(message="Description cannot be Empty")
    private String description;

    @NotNull(message="Category field is required")
    @NotEmpty(message="Category field cannot be Empty")
    @Pattern(regexp = "[A-Z][a-z]*", message="Cannot contain a number")
    private String category;

    public MovieDto() {
    }

    public MovieDto(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
