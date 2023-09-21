package com.example.moviebooking.movie.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @JsonProperty
    private String id;

    @Column
    @JsonProperty
    private String name;

    @Column
    @JsonProperty
    private String rating;

    @Column
    @JsonProperty
    private String genre;

    @Column
    @JsonProperty
    private int running_time_in_mins;

    @Column
    @JsonProperty
    private String image_url;

    public Movie(){
    }

    public Movie(String id, String name, String rating, String genre, int running_time_in_mins, String image_url) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.genre = genre;
        this.running_time_in_mins = running_time_in_mins;
        this.image_url = image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRunning_time_in_mins() {
        return running_time_in_mins;
    }

    public void setRunning_time_in_mins(int running_time_in_mins) {
        this.running_time_in_mins = running_time_in_mins;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
