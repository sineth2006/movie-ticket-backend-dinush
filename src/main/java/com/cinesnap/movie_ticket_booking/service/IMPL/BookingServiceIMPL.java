package com.cinesnap.movie_ticket_booking.service.IMPL;

import com.cinesnap.movie_ticket_booking.dao.BookingDao;
import com.cinesnap.movie_ticket_booking.dto.BookingDTO;
import com.cinesnap.movie_ticket_booking.entities.BookingEntity;
import com.cinesnap.movie_ticket_booking.entities.MovieEntity;
import com.cinesnap.movie_ticket_booking.entities.ShowEntity;
import com.cinesnap.movie_ticket_booking.exception.DataNotFoundException;
import com.cinesnap.movie_ticket_booking.service.BookingService;
import com.cinesnap.movie_ticket_booking.utill.Conversion;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceIMPL implements BookingService {

    private final BookingDao bookingDao;
    private  final Conversion conversion;

    @Override
    public void savebooking(BookingDTO booking) {
        BookingEntity bookingEntity = conversion.toBookingEntity(booking);
        bookingDao.save(bookingEntity);
    }

    @Override
    public BookingDTO getselectebooking(String bookingId) throws ChangeSetPersister.NotFoundException {
        BookingEntity bookingEntity=bookingDao.findById(bookingId)
                .orElseThrow(() -> new DataNotFoundException("Booking not found!"));
        return conversion.toBookingDTO(bookingEntity);
    }

    @Override
    public List<BookingDTO> getallbookings() {
        return conversion.toBookingDTOList(bookingDao.findAll());
    }

    @Override
    public void deletebooking(String bookingId) {
        BookingEntity foundbooking = bookingDao.findById(bookingId)
                .orElseThrow(() -> new DataNotFoundException("Booking not found!"));
        bookingDao.delete(foundbooking);
    }
}
