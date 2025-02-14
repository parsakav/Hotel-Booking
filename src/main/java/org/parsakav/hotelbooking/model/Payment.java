package org.parsakav.hotelbooking.model;

public class Payment {
    public enum PaymentMethod { CASH, CREDIT_CARD, DEBIT_CARD, ONLINE }

    private Long id;
    private Long bookingId;
    private double amount;
    private PaymentMethod paymentMethod;

    // Constructors
    public Payment() {}
    public Payment(Long id, Long bookingId, double amount, PaymentMethod paymentMethod) {
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
}