package com.cinesnap.movie_ticket_booking.entities;

import com.cinesnap.movie_ticket_booking.dto.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bookings")
public class BookingEntity implements Serializable {
    @Id
    private String bookingId;
    private String seatNumber;
    private String numberOfTickets;
    private String totalAmount;
    private String bookingDate;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;//ok

    @ManyToOne
    @JoinColumn(name = "show_id")
    private ShowEntity show;

    @OneToOne(mappedBy = "booking")
    private PaymentEntity payment;
}
