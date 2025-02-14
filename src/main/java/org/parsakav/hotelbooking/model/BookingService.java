package org.parsakav.hotelbooking.model;

public class BookingService  {
    private Long bookingId;
    private Long serviceId;

    // Constructors
    public BookingService() {}

    public BookingService(Long bookingId, Long serviceId) {
        this.bookingId = bookingId;
        this.serviceId = serviceId;
    }

    // Getters and Setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public Long getServiceId() { return serviceId; }
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }
}