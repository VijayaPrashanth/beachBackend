package com.example.moviebooking.movie;

import com.example.moviebooking.movie.repository.Movie;
import com.example.moviebooking.movie.view.MovieController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerIntegrationTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnAllMovies() throws Exception {
        Movie movie1 = new Movie("m101","The Shawshank Redemption","9.3","Drama",150,"https://www.movieposters.com/cdn/shop/products/pulpfiction.2436_480x.progressive.jpg");
        Movie movie2 = new Movie("m102","Interstellar","8.5","Drama",150,"https://www.movieposters.com/cdn/shop/products/pulpfiction.2436_480x.progressive.jpg");
        Movie movie3 = new Movie("m103","Kill Bill","8.3","Drama",150,"https://www.movieposters.com/cdn/shop/products/pulpfiction.2436_480x.progressive.jpg");

        List<Movie> movieList = Arrays.asList(movie1,movie2,movie3);
        String response = new ObjectMapper().writeValueAsString(movieList);

        //when(movieService.getMovieList()).thenReturn(movieList);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                        .accept(MediaType.APPLICATION_JSON)
                .content(response))
                .andExpect(status().isOk());
    }
}
