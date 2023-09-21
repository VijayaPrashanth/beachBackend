package com.example.moviebooking.movie;

import com.example.moviebooking.movie.repository.Movie;
import com.example.moviebooking.movie.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnAllMovies() {
        Movie movie1 = new Movie("m101","The Shawshank Redemption","9.3","Drama",150,"https://www.movieposters.com/cdn/shop/products/pulpfiction.2436_480x.progressive.jpg");
        Movie movie2 = new Movie("m102","Interstellar","8.5","Drama",150,"https://www.movieposters.com/cdn/shop/products/pulpfiction.2436_480x.progressive.jpg");
        Movie movie3 = new Movie("m103","Kill Bill","8.3","Drama",150,"https://www.movieposters.com/cdn/shop/products/pulpfiction.2436_480x.progressive.jpg");

        List<Movie> movieList = Arrays.asList(movie1,movie2,movie3);

        when(movieRepository.findAll()).thenReturn(movieList);

        List<Movie> response = movieService.getMovieList();

        assertEquals(movieList,response);
    }
}