package org.parsakav.hotelbooking.service;


import org.parsakav.hotelbooking.dto.PaymentDTO;
import org.parsakav.hotelbooking.model.Booking;
import org.parsakav.hotelbooking.model.Payment;
import org.parsakav.hotelbooking.repository.BookingRepository;
import org.parsakav.hotelbooking.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository; // برای لغو رزرو در صورت خطا

    public PaymentServiceImpl(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    private PaymentDTO convertToDTO(Payment payment) {
        return new PaymentDTO(payment.getId(), payment.getBookingId(), payment.getAmount(), payment.getPaymentMethod());
    }

    private Payment convertToEntity(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setBookingId(dto.getBookingId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        return payment;
    }

    @Override
    @Transactional
    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        Payment savedPayment = paymentRepository.save(payment);


        if (savedPayment.getAmount() <= 0) {
            Booking booking = bookingRepository.findById(savedPayment.getBookingId());
            if (booking != null) {
                bookingRepository.delete(booking);
            }
            throw new RuntimeException("Payment failed due to invalid amount. Booking has been cancelled.");
        }

        return convertToDTO(savedPayment);
    }

    @Override
    public PaymentDTO updatePayment(PaymentDTO paymentDTO) {
        if (paymentDTO.getId() == null) {
            throw new IllegalArgumentException("Payment ID must be provided for update.");
        }
        Payment payment = convertToEntity(paymentDTO);
        paymentRepository.update(payment);
        Payment updated = paymentRepository.findById(payment.getId());
        if (updated == null) {
            throw new RuntimeException("Payment not found with id: " + payment.getId());
        }
        return convertToDTO(updated);
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id);
        if (payment == null) {
            throw new RuntimeException("Payment not found with id: " + id);
        }
        paymentRepository.delete(payment);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id);
        if (payment == null) {
            throw new RuntimeException("Payment not found with id: " + id);
        }
        return convertToDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}