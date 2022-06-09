package com.sd.springfinalproject.service;

import com.sd.springfinalproject.dao.MovieListRepository;
import com.sd.springfinalproject.entity.MovieList;
import com.sd.springfinalproject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieListServiceImpl implements MovieListService{
    @Autowired
    private MovieListRepository movieListRepository;

    @Override
    public MovieList findById(int id) {
        Optional<MovieList> optionalMovieList = movieListRepository.findById(id);

        MovieList movieList = null;
        if(optionalMovieList.isPresent()){
            movieList = optionalMovieList.get();
        }else{throw new RuntimeException("The Movie is not found "+id);}
        return movieList;
    }

    @Override
    public List<MovieList> findAll() {
        return movieListRepository.findAll();
    }

    @Override
    public void save(MovieList movie) {
        movieListRepository.save(movie);
    }

    @Override
    public void deleteById(int id) {
        movieListRepository.deleteById(id);
    }
}
