package com.cinesnap.movie_ticket_booking.entities;

import com.cinesnap.movie_ticket_booking.dto.enums.PaymentStatus;
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
@Table(name = "payments")
public class PaymentEntity implements Serializable {
    @Id
    private String paymentId;
    private String amount;
    private String paymentDate;
    private String paymentMethod;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;
}
