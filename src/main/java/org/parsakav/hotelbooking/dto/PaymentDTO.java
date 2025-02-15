package org.parsakav.hotelbooking.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.parsakav.hotelbooking.model.Payment;

public class PaymentDTO {
    private Long id;

    @NotNull(message = "Booking ID is required")
    private Long bookingId;

    @Positive(message = "Amount must be positive")
    private double amount;

    @NotNull(message = "Payment method is required")
    private Payment.PaymentMethod paymentMethod;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Long bookingId, double amount, Payment.PaymentMethod paymentMethod) {
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Payment.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Payment.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}