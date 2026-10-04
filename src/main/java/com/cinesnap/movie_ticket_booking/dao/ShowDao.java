package com.cinesnap.movie_ticket_booking.dao;

import com.cinesnap.movie_ticket_booking.entities.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cinesnap.movie_ticket_booking.dto.enums.ShowStatus;

import java.util.List;

@Repository
public interface ShowDao extends JpaRepository<ShowEntity,String> {
    List<ShowEntity> findByMovieMovieId(String movieId);
    boolean existsByTheatreTheatreIdAndShowDateAndShowTime(String theatreId, String showDate, String showTime);
    List<ShowEntity> findByMovie_MovieIdAndStatus(String movieId, ShowStatus status);
}
