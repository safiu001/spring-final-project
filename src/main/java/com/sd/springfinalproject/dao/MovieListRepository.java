package com.sd.springfinalproject.dao;

import com.sd.springfinalproject.entity.MovieList;
import com.sd.springfinalproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieListRepository extends JpaRepository<MovieList, Integer> {
}
