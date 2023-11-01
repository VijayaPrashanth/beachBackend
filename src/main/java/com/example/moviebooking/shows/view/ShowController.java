package com.example.moviebooking.shows.view;

import com.example.moviebooking.movie.repository.Movie;
import com.example.moviebooking.shows.repository.Show;
import com.example.moviebooking.shows.ShowResponse;
import com.example.moviebooking.shows.ShowService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<ShowResponse> getShows(@Validated @RequestParam(name="date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<ShowResponse> showResponseList = new ArrayList();
        for (Show show:showService.fetchAll(date)) {
            showResponseList.add(constructShowResponse(show));
        }
        return showResponseList;
    }

    @GetMapping("/{movieId}")
    public List<ShowResponse> getShowByMovieId(@PathVariable String movieId){
        List<ShowResponse> showResponseList = new ArrayList<>();
        for(Show show:showService.fetchShowsByMovieID(movieId)){
            showResponseList.add(constructShowResponse(show));
        }
        return showResponseList;
    }

    private ShowResponse constructShowResponse(Show show) {
        Movie movie = showService.getMovieById(show.getMovieId());
        return new ShowResponse(show,show.getSlot(),movie);
    }

}
