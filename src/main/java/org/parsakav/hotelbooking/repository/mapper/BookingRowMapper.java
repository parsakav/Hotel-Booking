package org.parsakav.hotelbooking.repository.mapper;


import org.parsakav.hotelbooking.model.Booking;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class BookingRowMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getLong("id"));
        booking.setCustomerId(rs.getLong("customer_id"));
        booking.setRoomId(rs.getLong("room_id"));
        booking.setBookingDate(rs.getDate("booking_date").toLocalDate());
        booking.setCheckInDate(rs.getDate("check_in_date").toLocalDate());
        booking.setCheckOutDate(rs.getDate("check_out_date").toLocalDate());
        return booking;
    }
}