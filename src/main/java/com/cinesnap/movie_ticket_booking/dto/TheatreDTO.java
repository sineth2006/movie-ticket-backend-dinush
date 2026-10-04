package com.cinesnap.movie_ticket_booking.dto;

import com.cinesnap.movie_ticket_booking.dto.enums.Role;
import com.cinesnap.movie_ticket_booking.dto.enums.TheatreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheatreDTO implements Serializable {

    private String theatreId;
    private String theatreName;
    private String location;
    private String capacity;
    private TheatreStatus status;
}
