package com.cinesnap.movie_ticket_booking.entities;

import com.cinesnap.movie_ticket_booking.dto.enums.ShowStatus;
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
@Table(name = "shows")
public class ShowEntity implements Serializable {
    @Id
    private String showId;
    private String showDate;
    private String showTime;
    private String ticketPrice;
    @Enumerated(EnumType.STRING)
    private ShowStatus status;

    @OneToMany(mappedBy = "show")
    @JsonIgnore
    @ToString.Exclude
    private List<BookingEntity> bookings;//ok


    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;//ok

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private TheatreEntity theatre;//ok
}
