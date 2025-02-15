package org.parsakav.hotelbooking.model.mapper;


import org.parsakav.hotelbooking.model.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getLong("id"));
        payment.setBookingId(rs.getLong("booking_id"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setPaymentMethod(Payment.PaymentMethod.valueOf(rs.getString("payment_method")));
        return payment;
    }
}