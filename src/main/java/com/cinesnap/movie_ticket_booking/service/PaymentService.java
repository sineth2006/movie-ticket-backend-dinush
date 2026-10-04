package com.cinesnap.movie_ticket_booking.service;

import com.cinesnap.movie_ticket_booking.dto.PaymentDTO;

public interface PaymentService {
    void savepayment(PaymentDTO paymentDTO);
    PaymentDTO getselectedpayment(String paymentId);
    void processpayment(String paymentId);
    String checkpaymentstatus(String paymentId);
}
