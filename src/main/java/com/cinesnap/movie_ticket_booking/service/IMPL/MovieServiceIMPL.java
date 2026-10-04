package com.cinesnap.movie_ticket_booking.service.IMPL;


import com.cinesnap.movie_ticket_booking.dao.MovieDao;
import com.cinesnap.movie_ticket_booking.dto.MovieDTO;
import com.cinesnap.movie_ticket_booking.entities.MovieEntity;
import com.cinesnap.movie_ticket_booking.exception.DataNotFoundException;
import com.cinesnap.movie_ticket_booking.service.MovieService;
import com.cinesnap.movie_ticket_booking.utill.Conversion;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieServiceIMPL implements MovieService {

    private final MovieDao movieDao;
    private final Conversion conversion;


    private MovieDTO convertToDTO(MovieEntity entity) {
        MovieDTO dto = new MovieDTO();
        dto.setMovieId(entity.getMovieId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setDuration(entity.getDuration());
        dto.setLanguage(entity.getLanguage());
        dto.setGenre(entity.getGenre());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setStatus(entity.getStatus());
        dto.setImage_url(entity.getImage_url());
        return dto;
    }

    @Override
    public void savemovie(MovieDTO movie) {
        MovieEntity movieEntity = conversion.toMovieEntity(movie);
        movieDao.save(movieEntity);
    }

    @Override
    public MovieDTO getselectecmovie(String movieId) {
        MovieEntity movieEntity = movieDao.findById(movieId)
                .orElseThrow(() -> new DataNotFoundException("Movie not found!"));
        return conversion.toMovieDTO(movieEntity);

    }

    @Override
    public List<MovieDTO> getallmovies() {
        return conversion.toMovieDTOList(movieDao.findAll());
    }

    @Override
    public void updatemovie(String MovieId, MovieDTO movie) {
        MovieEntity foundMovie = movieDao.findById(MovieId)
                .orElseThrow(() -> new RuntimeException("Movie not found!"));
        foundMovie.setTitle(movie.getTitle());
        foundMovie.setDescription(movie.getDescription());
        foundMovie.setDuration(movie.getDuration());
        foundMovie.setLanguage(movie.getLanguage());
        foundMovie.setGenre(movie.getGenre());
        foundMovie.setReleaseDate(movie.getReleaseDate());
        foundMovie.setStatus(movie.getStatus());
        foundMovie.setImage_url(movie.getImage_url());
    }

    @Override
    public void deletemovie(String MovieId) {
        MovieEntity foundMovie = movieDao.findById(MovieId)
                .orElseThrow(() -> new DataNotFoundException("Movie not found!"));
        movieDao.delete(foundMovie);

    }


    @Override
    public List<MovieDTO> searchMoviesByTitle(String title) {
        List<MovieEntity> entities = movieDao.findByTitleContainingIgnoreCase(title);

        return entities.stream()
                .map(this::convertToDTO) // Reuses the helper method
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> getMoviesByGenre(String genre) {
        List<MovieEntity> entities = movieDao.findByGenreContainingIgnoreCase(genre);

        return entities.stream()
                .map(this::convertToDTO) // Reuses the helper method
                .collect(Collectors.toList());
    }
}