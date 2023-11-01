package com.example.moviebooking.shows;

import com.example.moviebooking.movie.repository.Movie;
import com.example.moviebooking.movie.repository.MovieRepository;
import com.example.moviebooking.shows.repository.Show;
import com.example.moviebooking.shows.repository.ShowRepository;
import com.example.moviebooking.slots.Slot;
import com.example.moviebooking.slots.SlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class ShowServiceTest {
    private ShowRepository showRepository;
    private MovieRepository movieRepository;

    private SlotRepository slotRepository;

    @BeforeEach
    public void beforeEach() {
        showRepository = mock(ShowRepository.class);
        movieRepository = mock(MovieRepository.class);
        slotRepository = mock(SlotRepository.class);
    }

    @Test
    public void should_get_movie_by_id() throws Exception {
        Movie movie = mock(Movie.class);
        when(movieRepository.findMovieById("movie_1")).thenReturn(movie);
        ShowService showService = new ShowService(showRepository, movieRepository);
        showService.getMovieById("movie_1");

        verify(movieRepository).findMovieById("movie_1");
    }

    @Test
    public void should_fetch_all_shows_and_set_movie_gateway() {
        List<Show> shows = new ArrayList<>();
        Slot slotOne = new Slot("slot1", Time.valueOf("09:00:00"), Time.valueOf("12:30:00"));
        Slot slotTwo = new Slot("slot1", Time.valueOf("09:00:00"), Time.valueOf("12:30:00"));
        Show showOne = new Show("m110",
                Date.valueOf("2023-11-02"),
                slotOne,
                new BigDecimal("299.99")
        );

        Show showTwo = new Show("m110",
                Date.valueOf("2023-11-02"),
                slotTwo,
                new BigDecimal("299.99")
        );

        Date date = Date.valueOf("2023-11-02");

        shows.add(showOne);
        shows.add(showTwo);

        when(showRepository.findByDate(date)).thenReturn(shows);
        ShowService showService = new ShowService(showRepository, movieRepository);

        List<Show> actualShows = showService.fetchAll(date);

        List<Show> expectedShows = new ArrayList<>();
        expectedShows.add(showOne);
        expectedShows.add(showTwo);

        assertThat(actualShows, is(equalTo(expectedShows)));
    }
}

