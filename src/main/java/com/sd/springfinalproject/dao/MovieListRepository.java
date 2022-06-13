package com.sd.springfinalproject.dao;

import com.sd.springfinalproject.entity.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieListRepository extends JpaRepository<MovieList, Integer> {
}
