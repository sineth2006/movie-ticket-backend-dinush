package com.cinesnap.movie_ticket_booking.dao;

import com.cinesnap.movie_ticket_booking.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDao extends JpaRepository<MovieEntity,String> {
    List<MovieEntity> findByTitleContainingIgnoreCase(String title);
    List<MovieEntity> findByGenreContainingIgnoreCase(String genre);
}
