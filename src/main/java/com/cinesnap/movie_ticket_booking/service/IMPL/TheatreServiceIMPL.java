package com.cinesnap.movie_ticket_booking.service.IMPL;

import com.cinesnap.movie_ticket_booking.dao.TheatreDao;
import com.cinesnap.movie_ticket_booking.dto.TheatreDTO;
import com.cinesnap.movie_ticket_booking.entities.TheatreEntity;
import com.cinesnap.movie_ticket_booking.exception.DataNotFoundException;
import com.cinesnap.movie_ticket_booking.service.TheatreService;
import com.cinesnap.movie_ticket_booking.utill.Conversion;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TheatreServiceIMPL implements TheatreService {

    private final TheatreDao theatreDao;
    private  final Conversion conversion;

    @Override
    public void savetheatre(TheatreDTO theatre) {
        TheatreEntity theatreEntity = conversion.toTheatreEntity(theatre);
        theatreDao.save(theatreEntity);
    }

    @Override
    public TheatreDTO getselectetheatre(String theatreid) throws ChangeSetPersister.NotFoundException {
        TheatreEntity theatreEntity=theatreDao.findById(theatreid)
         .orElseThrow(() -> new DataNotFoundException("User not found!"));
        return conversion.toTheatreDTO(theatreEntity);
    }

    @Override
    public List<TheatreDTO> getalltheatres() {
        return conversion.toTheatreDTOList(theatreDao.findAll());
    }

    @Override
    public void updatetheatre(String theatreId, TheatreDTO theatre) {
        TheatreEntity foundTheater = theatreDao.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theater not found!"));

        foundTheater.setTheatreName(theatre.getTheatreName());
        foundTheater.setLocation(theatre.getLocation());
        foundTheater.setCapacity(theatre.getCapacity());
        foundTheater.setStatus(theatre.getStatus());
    }

    @Override
    public void deletetheatre(String theatreId) {
        TheatreEntity foundTheater = theatreDao.findById(theatreId)
                .orElseThrow(() -> new DataNotFoundException("Theater not found!"));
        theatreDao.delete(foundTheater);
    }
}
