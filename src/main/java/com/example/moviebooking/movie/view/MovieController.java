package com.example.moviebooking.movie.view;

import com.example.moviebooking.movie.MovieService;
import com.example.moviebooking.movie.repository.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<Movie> getMovies() {
        return movieService.getMovieList();
    }
}
