package com.cinesnap.movie_ticket_booking.dto;

import com.cinesnap.movie_ticket_booking.dto.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO implements Serializable {

    private String paymentId;
    private String bookingId;
    private String amount;
    private String paymentDate;
    private String paymentMethod;
    private PaymentStatus status;
}
