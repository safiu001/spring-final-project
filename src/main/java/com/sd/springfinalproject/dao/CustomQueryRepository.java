package com.sd.springfinalproject.dao;

import com.sd.springfinalproject.entity.MovieList;

import java.util.List;

public interface CustomQueryRepository {
    List<MovieList> findAllByUsername(String username);
}
