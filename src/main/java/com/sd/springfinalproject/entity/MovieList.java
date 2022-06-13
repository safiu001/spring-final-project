package com.sd.springfinalproject.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="movie_list")
public class MovieList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="category")
    private String category;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(name = "customer_list",
            joinColumns=@JoinColumn(name="movie_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<Users> users;

    public MovieList() {
    }

    public MovieList(int id, String name, String description, String category, List<Users> users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.users = users;
    }

    public MovieList(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
