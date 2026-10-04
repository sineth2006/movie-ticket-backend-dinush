package com.cinesnap.movie_ticket_booking.entities;


import com.cinesnap.movie_ticket_booking.dto.enums.MovieStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class MovieEntity implements Serializable {
    @Id
    private String movieId;
    private String title;
    private String description;
    private String duration;
    private String language;
    private String genre;
    private String releaseDate;
    @Enumerated(EnumType.STRING)
    private MovieStatus status;
    private String image_url;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    @ToString.Exclude
    private List<ShowEntity> shows;



}
