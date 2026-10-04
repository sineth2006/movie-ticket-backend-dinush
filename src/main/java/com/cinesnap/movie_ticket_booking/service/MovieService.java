package com.cinesnap.movie_ticket_booking.service;

import com.cinesnap.movie_ticket_booking.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    void savemovie(MovieDTO movie);
    MovieDTO  getselectecmovie(String MovieId);
    List<MovieDTO> getallmovies();
    void updatemovie(String MovieId, MovieDTO movie);
    void deletemovie(String MovieId);
    List<MovieDTO> searchMoviesByTitle(String title);
    List<MovieDTO> getMoviesByGenre(String genre);

}
