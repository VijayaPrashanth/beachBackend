package com.example.moviebooking.shows.view;

import com.example.moviebooking.movie.repository.Movie;
import com.example.moviebooking.movie.repository.MovieRepository;
import com.example.moviebooking.shows.ShowResponse;
import com.example.moviebooking.shows.ShowService;
import com.example.moviebooking.shows.repository.Show;
import com.example.moviebooking.shows.repository.ShowRepository;
import com.example.moviebooking.slots.Slot;
import com.example.moviebooking.slots.SlotRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@WithMockUser
public class ShowControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SlotRepository slotRepository;

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private ShowService showService;


    @BeforeEach
    public void beforeAll() {
        showService = Mockito.mock(ShowService.class);
        showRepository.deleteAll();
        slotRepository.deleteAll();
    }

    @AfterEach
    public void afterAll() {
        showRepository.deleteAll();
        slotRepository.deleteAll();
    }

    @Test
    public void shouldReturnShows() throws Exception {
        Movie movie1 = new Movie("m111",
                "Inglourious bastards",
                "8.3",
                "Thriller",
                153,
                "https:/movieposters.com/TheShining");
        Movie movie2 = new Movie("m112",
                "The good, the bad and the ugly",
                "8.8",
                "Thriller",
                161,
                "https:/movieposters.com/TheShining");
        when(movieRepository.findMovieById("m111")).thenReturn(movie1);
        when(movieRepository.findMovieById("m112")).thenReturn(movie2);


        Slot slotOne = slotRepository.save( new Slot("slot1", Time.valueOf("09:30:00"),Time.valueOf("12:30:00")));
        Slot slotTwo = slotRepository.save( new Slot("slot2", Time.valueOf("13:30:00"),Time.valueOf("17:00:00")));
        Show showOne = showRepository.save( new Show(movie1.getId(), Date.valueOf("2023-10-11"),slotOne,new BigDecimal("155.53")));
        Show showTwo = showRepository.save( new Show(movie2.getId(), Date.valueOf("2023-10-11"),slotTwo,new BigDecimal("155.53")));


        List<Show> showList = Arrays.asList(showOne,showTwo);

        when(showService.fetchAll(Date.valueOf("2023-10-11"))).thenReturn(showList);
        when(showService.getMovieById(showOne.getMovieId())).thenReturn(movie1);
        when(showService.getMovieById(showTwo.getMovieId())).thenReturn(movie2);

        ShowResponse showResponse1 = new ShowResponse(showOne,slotOne,showService.getMovieById(showOne.getMovieId()));
        ShowResponse showResponse2 = new ShowResponse(showTwo,slotTwo,showService.getMovieById(showTwo.getMovieId()));
        List<ShowResponse> showResponseList = Arrays.asList(showResponse1,showResponse2);


        mockMvc.perform(MockMvcRequestBuilders.get("/shows?date=2023-10-11"))
                .andExpect(status().isOk())
                .andExpect(content().json("[" +
                        "{'id':" + showOne.getId() + ",'date':'2023-10-11','cost':155.53," +
                        "'slot':{'id':" + 1 + ",'name':'slot1','startTime':'09:30:00','endTime':'12:30:00'}," +
                        "'movie':{'id':'m111','name':'Inglourious bastards','genre':'Thriller','running_time_in_mins':"+153+",'image_url':'https:/movieposters.com/TheShining'}}," +
                        "{'id':" + showTwo.getId() + ",'date':'2023-10-11','cost':155.53," +
                        "'slot':{'id':" + 2 + ",'name':'slot2','startTime':'13:30:00','endTime':'17:00:00'}," +
                        "'movie':{'id':'m112','name':'The good, the bad and the ugly','genre':'Thriller','running_time_in_mins':"+161+",'image_url':'https:/movieposters.com/TheShining'}}" +
                        "]"));

    }
}
