package com.sd.springfinalproject.controller;

import com.sd.springfinalproject.dto.UsersDto;
import com.sd.springfinalproject.entity.MovieList;
import com.sd.springfinalproject.entity.Users;
import com.sd.springfinalproject.service.MovieListService;
import com.sd.springfinalproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {
    private MovieListService movieListService;
    private UsersService usersService;
    private final String movieList = "/app/movieList";

    public AppController(MovieListService movieListService, UsersService usersService){
        this.movieListService = movieListService;
        this.usersService = usersService;
    }

    @RequestMapping("/home")
    public String home(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UsersDto user = usersService.getUsersDto(username);
        model.addAttribute("user", user);
        return "homepage";
    }

    @GetMapping("/movieList")
    public String movieList(Model model){
        List<MovieList> movies = movieListService.findAll();
        model.addAttribute("movies", movies);
        return "movie-list";
    }

    @GetMapping("/addMovie")
    public String addMovie(Model model){
        model.addAttribute("movie", new MovieList());
        return "movie-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("movie") MovieList movie){
        movieListService.save(movie);
        return "redirect:"+movieList;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("movieId") int movieId){
        movieListService.deleteById(movieId);
        return "redirect:"+movieList;
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("movieId") int movieId, Model model){
        MovieList movie = movieListService.findById(movieId);
        model.addAttribute("movie", movie);

        return "movie-form";
    }

    @GetMapping("/myList")
    public String myList(Model model, Principal principal){
        String username = principal.getName();
        Users user = usersService.findByUsername(username);

        List<MovieList> myMovies = user.getMovies();
        model.addAttribute("movies", myMovies);
        return "my-list";
    }

    @GetMapping("/addToMyList")
    public String addToMyList(@RequestParam("movieId") int movieId, Principal principal){
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        MovieList movie = movieListService.findById(movieId);
        user.add(movie);
        usersService.save(user);
        return "redirect:"+movieList;
    }

    @GetMapping("/deleteFromMyList")
    public String deleteFromMyList(@RequestParam("movieId") int movieId, Principal principal){
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        List<MovieList> movies = user.getMovies();
        MovieList movie = movieListService.findById(movieId);
        movies.remove(movie);
        usersService.save(user);
        return "redirect:/app/myList";
    }
}