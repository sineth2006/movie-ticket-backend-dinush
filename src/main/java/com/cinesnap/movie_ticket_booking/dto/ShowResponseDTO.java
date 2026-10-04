package com.cinesnap.movie_ticket_booking.dto;

import com.cinesnap.movie_ticket_booking.dto.enums.ShowStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponseDTO {
    private String showId;
    private String showDate;
    private String showTime;
    private ShowStatus status;
    private String ticketPrice;
    private String theatreId;
    private String theatreName;
    private String location;
}
