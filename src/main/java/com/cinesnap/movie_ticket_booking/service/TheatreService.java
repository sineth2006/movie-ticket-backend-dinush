package com.cinesnap.movie_ticket_booking.service;

import com.cinesnap.movie_ticket_booking.dto.TheatreDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TheatreService {
    void savetheatre(TheatreDTO theatre);
    TheatreDTO getselectetheatre(String theatreid) throws ChangeSetPersister.NotFoundException;
    List<TheatreDTO> getalltheatres();
    void updatetheatre(String theatreId, TheatreDTO theatre);
    void deletetheatre(String theatreId);
}