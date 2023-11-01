package com.example.moviebooking.shows;

import com.example.moviebooking.movie.repository.Movie;
import com.example.moviebooking.movie.repository.MovieRepository;
import com.example.moviebooking.shows.repository.Show;
import com.example.moviebooking.shows.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShowService {

    private ShowRepository showRepository;

    private MovieRepository movieRepository;

    public ShowService(ShowRepository showRepository, MovieRepository movieRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
    }

    public List<Show> fetchAll(Date date) {
         return showRepository.findByDate(date);
    }

    public Movie getMovieById(String movieId) {
        return movieRepository.findMovieById(movieId);
    }

    public List<Show> fetchShowsByMovieID(String movieId) {
        return showRepository.findByMovieId(movieId);
    }
}
