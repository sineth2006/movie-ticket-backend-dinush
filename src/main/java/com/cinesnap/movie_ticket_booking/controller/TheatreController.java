package com.cinesnap.movie_ticket_booking.controller;


import com.cinesnap.movie_ticket_booking.dto.TheatreDTO;
import com.cinesnap.movie_ticket_booking.dto.UserDTO;
import com.cinesnap.movie_ticket_booking.service.TheatreService;
import com.cinesnap.movie_ticket_booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/theatres")
@RestController
@RequiredArgsConstructor
public class TheatreController {
    private static final Logger logger = LoggerFactory.getLogger(TheatreController.class);
    private final TheatreService theatreService;

    ///--------save theatre--------///
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createtheatre(@RequestBody TheatreDTO theatreDTO) {
        logger.info("Incoming create theatre detail is {}", theatreDTO);
        theatreService.savetheatre(theatreDTO);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }
    ///--------get theatre by id--------///
    @GetMapping(value= "{theaterId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TheatreDTO> getSelectetheatre(@PathVariable String theaterId)throws ChangeSetPersister.NotFoundException  {
        System.out.println("Selected theater: " + theaterId);
        theatreService.getselectetheatre(theaterId);
        return new  ResponseEntity<>(theatreService.getselectetheatre(theaterId), HttpStatus.OK);
    }
    ///--------get all theatre--------///
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TheatreDTO>> getTheaters() {
        theatreService.getalltheatres();
        return new ResponseEntity<>(theatreService.getalltheatres(), HttpStatus.OK);
    }
    ///--------update theatre--------///
    @PatchMapping(value = "{theaterId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatetheater(@PathVariable String theaterId,@RequestBody TheatreDTO theatreDTO) {
        theatreService.updatetheatre(theaterId,theatreDTO);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    ///--------delete theatre--------///
    @DeleteMapping(value = "{theaterId}")
    public ResponseEntity<Void> deletetheater(@PathVariable String theaterId) {
        theatreService.deletetheatre(theaterId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
