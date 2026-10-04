package com.cinesnap.movie_ticket_booking.controller;

import com.cinesnap.movie_ticket_booking.dao.ShowDao;
import com.cinesnap.movie_ticket_booking.dto.MovieDTO;
import com.cinesnap.movie_ticket_booking.dto.ShowDTO;
import com.cinesnap.movie_ticket_booking.dto.ShowResponseDTO;
import com.cinesnap.movie_ticket_booking.dto.TheatreDTO;
import com.cinesnap.movie_ticket_booking.dto.enums.ShowStatus;
import com.cinesnap.movie_ticket_booking.entities.ShowEntity;
import com.cinesnap.movie_ticket_booking.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/shows")
@RestController
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;
    private final ShowDao showDao;

    ///--------save show--------///
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveshow(@RequestBody ShowDTO showDTO) {
        showService.saveshow(showDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    ///--------get show by id--------///
    @GetMapping(value = "{showId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShowDTO> getSelecteshow(@PathVariable String showId) throws ChangeSetPersister.NotFoundException {
        System.out.println("Selected show: " + showId);
        showService.getselectedshow(showId);
        return new ResponseEntity<>(showService.getselectedshow(showId), HttpStatus.OK);
    }
    ///--------get all show--------///
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShowDTO>> getShows() {
        showService.getallshows();
        return new ResponseEntity<>(showService.getallshows(), HttpStatus.OK);
    }
    ///--------update show--------///
    @PatchMapping(value = "{showId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateshow(@PathVariable String showId, @RequestBody ShowDTO showDTO) {
        showService.updateshow(showId, showDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    ///--------delete show--------///
    @DeleteMapping(value = "{showId}")
    public ResponseEntity<Void> deleteshow(@PathVariable String showId) {
        showService.deleteshow(showId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    ///--------get show by id(movie matching)--------///
    @GetMapping("/movies/{movieId}")
    public ResponseEntity<List<ShowResponseDTO>> getShowsForMovie(@PathVariable("movieId") String movieId) {
        List<ShowEntity> shows = showDao.findByMovie_MovieIdAndStatus(movieId, ShowStatus.SCHEDULED);

        List<ShowResponseDTO> responseList = shows.stream().map(show -> new ShowResponseDTO(
                show.getShowId(),
                show.getShowDate(),
                show.getShowTime(),
                show.getStatus(), // Convert Enum to String for clean JSON delivery
                show.getTicketPrice(),
                show.getTheatre().getTheatreId(),
                show.getTheatre().getTheatreName(),
                show.getTheatre().getLocation()
        )).collect(Collectors.toList());

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}