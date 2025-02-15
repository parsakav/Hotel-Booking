package org.parsakav.hotelbooking.service;


import org.parsakav.hotelbooking.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO processPayment(PaymentDTO paymentDTO);
    PaymentDTO updatePayment(PaymentDTO paymentDTO);
    void deletePayment(Long id);
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getAllPayments();
}