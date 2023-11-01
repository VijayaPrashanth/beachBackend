package com.example.moviebooking.slots;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;
import jakarta.persistence.*;

import java.sql.Time;

@Entity
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private int id;

    @Column(nullable = false)
    @JsonProperty
    private String name;

    @Column(nullable = false)
//    @JsonSerialize(using = TimeZoneSerializer.class)
    @JsonProperty
    private Time startTime;

    @Column(nullable = false)
//    @JsonSerialize(using = TimeZoneSerializer.class)
    @JsonProperty
    private Time endTime;


    public Slot() {
    }

    public Slot(String name, Time startTime, Time endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
