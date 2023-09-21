package com.example.moviebooking.movie;

import com.example.moviebooking.movie.repository.Movie;
import com.example.moviebooking.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovieList() {
        movieRepository.findAll();
        return null;
    }
}
