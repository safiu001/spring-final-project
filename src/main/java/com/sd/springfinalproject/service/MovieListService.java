package com.sd.springfinalproject.service;
import com.sd.springfinalproject.dto.MovieDto;
import com.sd.springfinalproject.entity.MovieList;

import java.util.List;

public interface MovieListService {
    MovieList findById(int id);
    List<MovieList> findAll();
    void save(MovieDto movie);
    void deleteById(int id);
}
