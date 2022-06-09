package com.sd.springfinalproject.service;
import com.sd.springfinalproject.entity.MovieList;
import com.sd.springfinalproject.entity.Users;

import java.util.List;

public interface MovieListService {
    MovieList findById(int id);
    List<MovieList> findAll();
    void save(MovieList movie);
    void deleteById(int id);
}
