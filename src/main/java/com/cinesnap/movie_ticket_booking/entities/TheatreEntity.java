package com.cinesnap.movie_ticket_booking.entities;

import com.cinesnap.movie_ticket_booking.dto.enums.TheatreStatus;
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
@Table(name = "theatres")
public class TheatreEntity implements Serializable {
    @Id
    private String theatreId;
    private String theatreName;
    private String location;
    private String capacity;
    @Enumerated(EnumType.STRING)
    private TheatreStatus status;

    @OneToMany(mappedBy = "theatre")
    @JsonIgnore
    @ToString.Exclude
    private List<ShowEntity> shows;
}
