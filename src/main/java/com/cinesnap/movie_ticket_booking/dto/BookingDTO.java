package com.cinesnap.movie_ticket_booking.dto;

import com.cinesnap.movie_ticket_booking.dto.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDTO implements Serializable {

    private String bookingId;
    private String userId;
    private String showId;
    private String seatNumber;
    private String numberOfTickets;
    private String totalAmount;
    private String bookingDate;
    private BookingStatus status;
}
