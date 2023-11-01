package com.example.moviebooking.shows.repository;

import com.example.moviebooking.slots.Slot;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "show")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @Column(nullable = false)
    @JsonProperty
    private String movieId;

    @Column(nullable = false, columnDefinition = "DATE")
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @OneToOne
    @JoinColumn(name = "slot_id")
    private Slot slot;

    @Column(nullable = false)
    @JsonProperty
    private BigDecimal cost;

    public Show(){}
    public Show(String movieId, Date date, Slot slot, BigDecimal cost) {
        this.movieId = movieId;
        this.date = date;
        this.slot = slot;
        this.cost = cost;
    }

    public Show(Show show) {
        this.id = show.id;
        this.movieId = show.movieId;
        this.date = show.date;
        this.slot = show.slot;
        this.cost = show.cost;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
