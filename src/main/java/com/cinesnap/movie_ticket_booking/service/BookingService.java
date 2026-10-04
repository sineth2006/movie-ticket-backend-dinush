package com.cinesnap.movie_ticket_booking.service;

import com.cinesnap.movie_ticket_booking.dto.BookingDTO;
import com.cinesnap.movie_ticket_booking.dto.TheatreDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface BookingService {
    void savebooking(BookingDTO booking);
    BookingDTO getselectebooking(String bookingId) throws ChangeSetPersister.NotFoundException;
    List<BookingDTO> getallbookings();
    void deletebooking(String bookingId);
}
