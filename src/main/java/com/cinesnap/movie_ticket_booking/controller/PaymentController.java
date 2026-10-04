package com.cinesnap.movie_ticket_booking.controller;

import com.cinesnap.movie_ticket_booking.dto.PaymentDTO;
import com.cinesnap.movie_ticket_booking.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/payments")
@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    ///--------save payment--------///
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> savePayment(@RequestBody PaymentDTO paymentDTO) {
        paymentService.savepayment(paymentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    ///--------get  payment by id--------///
    @GetMapping(value = "{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentDTO> getSelectedPayment(@PathVariable String paymentId) {
        System.out.println("Selected Payment: " + paymentId);

        return new ResponseEntity<>(paymentService.getselectedpayment(paymentId), HttpStatus.OK);
    }
    ///--------process payment--------///
    @PatchMapping("/process/{paymentId}")
    public ResponseEntity<Void> processPayment(@PathVariable String paymentId) {
        paymentService.processpayment(paymentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    ///--------check payment--------///
    @GetMapping(value = "/checkpayment/{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkPaymentStatus(@PathVariable String paymentId) {

        return new ResponseEntity<>(paymentService.checkpaymentstatus(paymentId), HttpStatus.OK);
    }
}
