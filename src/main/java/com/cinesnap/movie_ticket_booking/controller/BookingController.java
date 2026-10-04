package com.cinesnap.movie_ticket_booking.controller;

import com.cinesnap.movie_ticket_booking.dto.BookingDTO;
import com.cinesnap.movie_ticket_booking.dto.ShowDTO;
import com.cinesnap.movie_ticket_booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/bookings")
@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    ///--------save booking--------///
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> savebooking(@RequestBody BookingDTO bookingDTO) {
        bookingService.savebooking(bookingDTO);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }

    ///--------get booking by id--------///
    @GetMapping(value= "{bookingId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingDTO> getSelectebooking(@PathVariable String bookingId)throws ChangeSetPersister.NotFoundException  {
        System.out.println("Selected booking: " + bookingId);
        bookingService.getselectebooking(bookingId);
        return new  ResponseEntity<>(bookingService.getselectebooking(bookingId), HttpStatus.OK);
    }

    ///--------get all bookings--------///
    @GetMapping(value= "my-bookings",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookingDTO>> getBookings() {
        bookingService.getallbookings();
        return new ResponseEntity<>(bookingService.getallbookings(), HttpStatus.OK);
    }

    ///--------delete booking--------///
    @DeleteMapping(value = "{bookingId}/cancel")
    public ResponseEntity<Void> deleteBooking(@PathVariable String bookingId) {
        bookingService.deletebooking(bookingId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
