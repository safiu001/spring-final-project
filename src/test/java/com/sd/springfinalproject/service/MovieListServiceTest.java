package com.sd.springfinalproject.service;

import com.sd.springfinalproject.dao.MovieListRepository;
import com.sd.springfinalproject.dto.MovieDto;
import com.sd.springfinalproject.entity.MovieList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@SpringBootTest
class MovieListServiceTest {
    @MockBean
    MovieListRepository movieListRepository;

    @Autowired
    MovieListService movieListService;

    @Test
    void findById() {
//        Given
        MovieList movieList = new MovieList();
        movieList.setId(1);
        movieList.setName("Harry Potter and The Goblet of Fire");
        movieList.setCategory("Adventure");
        movieList.setDescription("Harry on Participates in the Tournament");

        given(movieListRepository.findById(1)).willReturn(Optional.of(movieList));

//        When
        MovieList  movie = movieListService.findById(1);

//        Then
        assertThat(movie.getName()).isEqualTo(movieList.getName());
        assertThat(movie.getCategory()).isEqualTo(movieList.getCategory());
        assertThat(movie.getDescription()).isEqualTo(movieList.getDescription());
        assertThat(movie.getId()).isEqualTo(movieList.getId());
    }

    @Test
    void testFindById_idNotFound(){
        Optional<MovieList> movie = Optional.empty();
        given(movieListRepository.findById(1)).willReturn(movie);
        Exception exception = assertThrows(RuntimeException.class, ()->movieListService.findById(1));

        String expectedMessage = "The Movie is not found 1";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).contains(expectedMessage);
    }

    @Test
    void findAll() {
        List<MovieList> movies = Arrays.asList(
                new MovieList(1, "Harry Potter", "Harry on new Adventure", "Adventure", null),
                new MovieList(1, "Pirates of the Caribbean", "Jack Sparrow in search of black pearl", "Comedy/Adventure", null)
        );
        given(movieListRepository.findAll()).willReturn(movies);

        List<MovieList> actualMovies = movieListService.findAll();
        assertThat(actualMovies).isEqualTo(movies);
    }

    @Test
    void save() {
        MovieDto movie = new MovieDto("Harry Potter", "Harry on new Adventure", "Adventure");
        movieListService.save(movie);

        verify(movieListRepository, times(1)).save(any());
    }

    @Test
    void saveUpdate() {
        MovieDto movie = new MovieDto("Harry Potter", "Harry on new Adventure", "Adventure");
        movie.setId(1);
        MovieList movieList = new MovieList(1, "Harry Potter", "Harry on new Adventure", "Adventure", null);

        when(movieListRepository.findById(1)).thenReturn(Optional.of(movieList));
        movieListService.save(movie);

        verify(movieListRepository, times(1)).save(any());
        assertThat(movieList).isEqualTo(movieListService.findById(1));
    }

    @Test
    void deleteById() {
        movieListService.deleteById(1);

        verify(movieListRepository, times(1)).deleteById(1);
    }
}