package com.example.moviebooking.shows.repository;

import com.example.moviebooking.shows.ShowResponse;
import com.example.moviebooking.shows.repository.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    @Query(value = "SELECT * FROM SHOW WHERE DATE=?1 order by slot_id", nativeQuery = true)
    List<Show> findByDate(Date date);

    @Query(value = "SELECT * FROM SHOW WHERE MOVIE_ID=?1 order by date ", nativeQuery = true)
    List<Show> findByMovieId(String movieId);
}
