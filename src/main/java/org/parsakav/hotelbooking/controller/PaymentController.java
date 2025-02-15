package org.parsakav.hotelbooking.controller;


import jakarta.validation.Valid;
import org.parsakav.hotelbooking.dto.PaymentDTO;
import org.parsakav.hotelbooking.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> processPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO processed = paymentService.processPayment(paymentDTO);
        return new ResponseEntity<>(processed, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PaymentDTO> updatePayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO updated = paymentService.updatePayment(paymentDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(id);
        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}