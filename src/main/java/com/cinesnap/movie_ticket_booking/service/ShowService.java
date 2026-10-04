package com.cinesnap.movie_ticket_booking.service;

import com.cinesnap.movie_ticket_booking.dto.ShowDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ShowService {
    void saveshow(ShowDTO show);
    ShowDTO getselectedshow(String showId) throws ChangeSetPersister.NotFoundException;
    List<ShowDTO> getallshows();
    void updateshow(String showId, ShowDTO show);
    void deleteshow(String showId);
    List<ShowDTO> getshowsformovie(String movieId);
}
