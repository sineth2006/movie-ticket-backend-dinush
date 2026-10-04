package com.cinesnap.movie_ticket_booking.service.IMPL;

import com.cinesnap.movie_ticket_booking.dao.MovieDao;
import com.cinesnap.movie_ticket_booking.dao.ShowDao;
import com.cinesnap.movie_ticket_booking.dao.TheatreDao;
import com.cinesnap.movie_ticket_booking.dto.ShowDTO;
import com.cinesnap.movie_ticket_booking.entities.MovieEntity;
import com.cinesnap.movie_ticket_booking.entities.ShowEntity;
import com.cinesnap.movie_ticket_booking.entities.TheatreEntity;
import com.cinesnap.movie_ticket_booking.exception.DataNotFoundException;
import com.cinesnap.movie_ticket_booking.service.ShowService;
import com.cinesnap.movie_ticket_booking.utill.Conversion;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShowServiceIMPL implements ShowService {

    private final ShowDao showDao;
    private final MovieDao movieDao;
    private final TheatreDao theatreDao;
    private  final Conversion conversion;

    @Override
    public void saveshow(ShowDTO show) {
        ShowEntity showEntity = conversion.toShowEntity(show);
        showDao.save(showEntity);
    }

    @Override
    public ShowDTO getselectedshow(String showId) throws ChangeSetPersister.NotFoundException {
        ShowEntity showEntity=showDao.findById(showId)
                .orElseThrow(() -> new DataNotFoundException("Show not found!"));
        return conversion.toShowDTO(showEntity);
    }

    @Override
    public List<ShowDTO> getallshows() {
        return conversion.toShowDTOList(showDao.findAll());
    }

    @Override
    public void updateshow(String showId, ShowDTO show) {
        ShowEntity foundShow = showDao.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found!"));

        MovieEntity foundmovie = movieDao.findById(show.getMovieId())
                .orElseThrow(() -> new DataNotFoundException("Movie not found!"));

        TheatreEntity foundtheatre = theatreDao.findById(show.getTheatreId())
                .orElseThrow(() -> new DataNotFoundException("Theatre not found!"));

        foundShow.setMovie(foundmovie);
        foundShow.setTheatre(foundtheatre);
        foundShow.setShowDate(show.getShowDate());
        foundShow.setShowTime(show.getShowTime());
        foundShow.setTicketPrice(show.getTicketPrice());
        foundShow.setStatus(show.getStatus());
    }

    @Override
    public void deleteshow(String showId) {
        ShowEntity foundShow = showDao.findById(showId)
                .orElseThrow(() -> new DataNotFoundException("Show not found!"));
        showDao.delete(foundShow);
    }


    @Override
    public List<ShowDTO> getshowsformovie(String movieId) {
        List<ShowEntity> shows = showDao.findByMovieMovieId(movieId);
        return conversion.toShowDTOList(shows);
    }
}
