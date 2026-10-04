package com.cinesnap.movie_ticket_booking.service.IMPL;

import com.cinesnap.movie_ticket_booking.dao.BookingDao;
import com.cinesnap.movie_ticket_booking.dao.PaymentDao;
import com.cinesnap.movie_ticket_booking.dto.PaymentDTO;
import com.cinesnap.movie_ticket_booking.dto.enums.BookingStatus;
import com.cinesnap.movie_ticket_booking.dto.enums.PaymentStatus;
import com.cinesnap.movie_ticket_booking.entities.BookingEntity;
import com.cinesnap.movie_ticket_booking.entities.PaymentEntity;
import com.cinesnap.movie_ticket_booking.exception.DataNotFoundException;
import com.cinesnap.movie_ticket_booking.service.PaymentService;
import com.cinesnap.movie_ticket_booking.utill.Conversion;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceIMPL implements PaymentService {

    private final PaymentDao paymentDao;
    private final BookingDao bookingDao;
    private  final Conversion conversion;

    @Override
    public void savepayment(PaymentDTO paymentDTO) {

        BookingEntity booking = bookingDao.findById(paymentDTO.getBookingId())
                .orElseThrow(() -> new DataNotFoundException("Booking not found!"));

        //  Cancelled bookings cannot be paid for ->business rulesd
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Cannot create payment for a cancelled booking.");
        }

        PaymentEntity paymentEntity = conversion.toPaymentEntity(paymentDTO);
        paymentEntity.setBooking(booking);
        paymentEntity.setStatus(PaymentStatus.PENDING); // get default stTUS

        paymentDao.save(paymentEntity);
    }

    @Override
    public PaymentDTO getselectedpayment(String paymentId) {
        PaymentEntity paymentEntity = paymentDao.findById(paymentId)
                .orElseThrow(() -> new DataNotFoundException("Payment not found!"));

        return conversion.toPaymentDTO(paymentEntity);
    }

    @Override
    public void processpayment(String paymentId) {
        PaymentEntity paymentEntity = paymentDao.findById(paymentId)
                .orElseThrow(() -> new DataNotFoundException("Payment not found!"));

        // Business Rule
        if (paymentEntity.getStatus() == PaymentStatus.COMPLETED) {
            throw new IllegalStateException("Payment has already been completed!");
        }

        BookingEntity booking = paymentEntity.getBooking();
        if (booking != null && booking.getStatus() == BookingStatus.CANCELLED) {
            paymentEntity.setStatus(PaymentStatus.FAILED);
            paymentDao.save(paymentEntity);
            throw new IllegalStateException("Payment failed because booking is cancelled.");
        }

        // Update payment status
        paymentEntity.setStatus(PaymentStatus.COMPLETED);

        // Business Rule
        if (booking != null) {
            booking.setStatus(BookingStatus.CONFIRMED);
            bookingDao.save(booking);
        }

        paymentDao.save(paymentEntity);
    }

    @Override
    public String checkpaymentstatus(String paymentId) {
        PaymentEntity paymentEntity = paymentDao.findById(paymentId)
                    .orElseThrow(() -> new DataNotFoundException("Payment not found!"));

        return paymentEntity.getStatus().name();
    }

}
