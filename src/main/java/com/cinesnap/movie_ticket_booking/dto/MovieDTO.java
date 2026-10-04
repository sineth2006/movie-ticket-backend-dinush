package com.cinesnap.movie_ticket_booking.dto;

import com.cinesnap.movie_ticket_booking.dto.enums.MovieStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO {

    private String movieId;
    private String title;
    private String description;
    private String duration;
    private String language;
    private String genre;
    private String releaseDate;
    private MovieStatus status;
    private String image_url;

}
