package com.sd.springfinalproject.controller;

import com.sd.springfinalproject.dto.MovieDto;
import com.sd.springfinalproject.dto.UsersDto;
import com.sd.springfinalproject.entity.Authorities;
import com.sd.springfinalproject.entity.MovieList;
import com.sd.springfinalproject.entity.Users;
import com.sd.springfinalproject.service.MovieListService;
import com.sd.springfinalproject.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
class AppControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private MovieListService movieListService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @WithMockUser("andy")
    void home() throws Exception {
        UsersDto user = new UsersDto();
        user.setUsername("andy");
        user.setFirstName("Andy");
        user.setLastName("Barnard");
        user.setFullName("Andy Barnard");
        user.setMessage("Hello");
        given(usersService.getUsersDto("andy")).willReturn(user);

        String url = "/app/home";
        mockMvc.perform(MockMvcRequestBuilders.get(url).with(csrf())).andExpect(status().isOk());
    }

    @Test
    void movieList() throws Exception{
        List<MovieList> movies = Arrays.asList(
                new MovieList(1, "Harry Potter and the Chambers Secret", "Harry on new Adventure", "Adventure", null),
                new MovieList(2, "Harry Potter and the goblet of fire", "Harry on new Adventure", "Adventure", null)
        );
        given(movieListService.findAll()).willReturn(movies);

        String url = "/app/movieList";
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
    }

    @Test
    void addMovie() throws Exception{
        String url = "/app/addMovie";
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
    }

    @Test
    void save() throws Exception{
        String url = "/app/save";
        MovieDto movie = new MovieDto("Harry Potter and the Chambers Secret", "Harry on new Adventure", "Adventure");
        mockMvc.perform(MockMvcRequestBuilders.post(url).flashAttr("movie", movie)).andExpect(status().is3xxRedirection());

        verify(movieListService, times(1)).save(movie);
    }

    @Test
    void successLogin() throws Exception{
        String url = "/app/successLogin";
        mockMvc.perform(MockMvcRequestBuilders.post(url)).andExpect(status().is3xxRedirection());
    }

    @Test
    void delete() throws Exception{
        String url = "/app/delete";
        int movieId = 1;

        mockMvc.perform(MockMvcRequestBuilders.get(url).param("movieId", String.valueOf(movieId))).andExpect(status().is3xxRedirection());
        verify(movieListService, times(1)).deleteById(movieId);
    }

    @Test
    void showUpdateForm() throws Exception{
        String url = "/app/showUpdateForm";
        int movieId = 1;
        MovieList movie = new MovieList(1, "Harry Potter and the Chambers Secret", "Harry on new Adventure", "Adventure", null);
        given(movieListService.findById(movieId)).willReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.get(url).param("movieId", String.valueOf(movieId)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("andy")
    void myList() throws Exception{
        Users user = new Users("andy", "andy123", true, "Andy", "Bernard");
        given(usersService.findByUsername("andy")).willReturn(user);

        String url = "/app/myList";
        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser("andy")
    void addToMyList() throws Exception{
        Users user = new Users("andy", "andy123", true, "Andy", "Bernard");
        given(usersService.findByUsername("andy")).willReturn(user);

        int movieId = 1;
        MovieList movie = new MovieList(1, "Harry Potter and the Chambers Secret", "Harry on new Adventure", "Adventure", null);
        given(movieListService.findById(movieId)).willReturn(movie);

        String url = "/app/addToMyList";
        mockMvc.perform(MockMvcRequestBuilders.get(url).with(csrf()).param("movieId", String.valueOf(movieId)))
                .andExpect(status().is3xxRedirection());

        verify(usersService, times(1)).save(user);
    }

    @Test
    @WithMockUser("andy")
    void deleteFromMyList() throws Exception{
        Users user = new Users("andy", "andy123", true, "Andy", "Bernard");
        int movieId = 1;

        List<Users> users = new ArrayList<>();
        users.add(user);
        MovieList movie = new MovieList(1, "Harry Potter and the Chambers Secret", "Harry on new Adventure", "Adventure", users);

        List<MovieList> moviesList = new ArrayList<>();
        moviesList.add(movie);
        user.setMovies(moviesList);

        given(usersService.findByUsername("andy")).willReturn(user);
        given(movieListService.findById(movieId)).willReturn(movie);

        String url = "/app/deleteFromMyList";
        mockMvc.perform(MockMvcRequestBuilders.get(url).with(csrf()).param("movieId", String.valueOf(movieId)))
                .andExpect(status().is3xxRedirection());

        verify(usersService, times(1)).save(user);
    }

    @Test
    @WithMockUser("andy")
    void homePage() throws Exception {
        UsersDto user = new UsersDto();
        user.setUsername("andy");
        user.setFirstName("Andy");
        user.setLastName("Barnard");
        user.setFullName("Andy Barnard");
        user.setMessage("Hello");
        given(usersService.getUsersDto("andy")).willReturn(user);

        String url = "/app/home";
        mockMvc.perform(MockMvcRequestBuilders.get(url).with(csrf())).andExpect(status().isOk());
    }
}