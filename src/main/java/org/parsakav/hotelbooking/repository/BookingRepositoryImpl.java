package org.parsakav.hotelbooking.repository;

import org.parsakav.hotelbooking.exceptions.DatabaseException;
import org.parsakav.hotelbooking.model.Booking;

import java.util.List;

import org.parsakav.hotelbooking.model.Customer;
import org.parsakav.hotelbooking.repository.mapper.BookingRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookingRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Booking save(Booking booking) {
        String sql = "INSERT INTO bookings (customer_id, room_id, booking_date, check_in_date, check_out_date) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, booking.getCustomerId());
            ps.setLong(2, booking.getRoomId());
            ps.setDate(3, Date.valueOf(booking.getBookingDate()));
            ps.setDate(4, Date.valueOf(booking.getCheckInDate()));
            ps.setDate(5, Date.valueOf(booking.getCheckOutDate()));
            return ps;
        }, keyHolder);
        booking.setId(keyHolder.getKey().longValue());
        return booking;
    }

    @Override
    public int update(Booking booking) {
        if (booking.getId() == null) {
            throw new IllegalArgumentException("Booking ID must be provided for update.");
        }
        String sql = "UPDATE bookings SET customer_id = ?, room_id = ?, booking_date = ?, check_in_date = ?, check_out_date = ? WHERE id = ?";
        return jdbcTemplate.update(sql, booking.getCustomerId(), booking.getRoomId(),
                Date.valueOf(booking.getBookingDate()), Date.valueOf(booking.getCheckInDate()),
                Date.valueOf(booking.getCheckOutDate()), booking.getId());
    }

    @Override
    public int delete(Booking booking) {
        if (booking.getId() == null) {
            throw new IllegalArgumentException("Booking ID must be provided for deletion.");
        }
        String sql = "DELETE FROM bookings WHERE id = ?";
        return jdbcTemplate.update(sql, booking.getId());
    }



    @Override
    public List<Booking> findAll() {
        String sql = "SELECT * FROM bookings";
        return jdbcTemplate.query(sql, new BookingRowMapper());
    }

    @Override
    public Booking findById(long id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id},new BookingRowMapper())).get();
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseException("Booking with id :"+id+" not found",e);
        } }
}