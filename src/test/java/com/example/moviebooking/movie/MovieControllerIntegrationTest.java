package com.example.moviebooking.movie;

import com.example.moviebooking.movie.repository.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MovieControllerIntegrationTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllMovies() throws Exception {
        Movie movie1 = new Movie("m101","The Shawshank Redemption","9.3","Drama",150,"https://www.movieposters.com/cdn/shop/products/pulpfiction.2436_480x.progressive.jpg");
        Movie movie2 = new Movie("m102","Interstellar","8.5","Drama",150,"https://www.movieposters.com/cdn/shop/products/pulpfiction.2436_480x.progressive.jpg");
        Movie movie3 = new Movie("m103","Kill Bill","8.3","Drama",150,"https://www.movieposters.com/cdn/shop/products/pulpfiction.2436_480x.progressive.jpg");

        List<Movie> movieList = Arrays.asList(movie1,movie2,movie3);
        String response = new ObjectMapper().writeValueAsString(movieList);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                .content(response))
                .andExpect(status().isOk());
    }
}
