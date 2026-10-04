package com.cinesnap.movie_ticket_booking.dto;

import com.cinesnap.movie_ticket_booking.dto.enums.ShowStatus;
import com.cinesnap.movie_ticket_booking.entities.MovieEntity;
import com.cinesnap.movie_ticket_booking.entities.TheatreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowDTO implements Serializable {

    private String showId;
    private String movieId;
    private String theatreId;
    private String showDate;
    private String showTime;
    private String ticketPrice;
    private ShowStatus status;
}
