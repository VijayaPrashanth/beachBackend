package com.example.moviebooking.shows;

import com.example.moviebooking.movie.repository.Movie;
import com.example.moviebooking.shows.repository.Show;
import com.example.moviebooking.slots.Slot;
import com.example.moviebooking.utilities.DateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.sql.Date;

public class ShowResponse {

    @JsonProperty
    private long id;

    @JsonProperty
    @JsonSerialize(using = DateSerializer.class)
    private Date date;

    @JsonProperty
    private Slot slot;

    @JsonProperty
    private BigDecimal cost;

    @JsonProperty
    private Movie movie;

    public ShowResponse(Show show, Slot slot, Movie movie) {
        this.id = show.getId();
        this.date = show.getDate();
        this.cost = show.getCost();
        this.slot = slot;
        this.movie = movie;
    }
}
