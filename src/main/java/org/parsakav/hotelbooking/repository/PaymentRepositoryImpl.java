package org.parsakav.hotelbooking.repository;

import org.parsakav.hotelbooking.exceptions.DatabaseException;
import org.parsakav.hotelbooking.model.Payment;
import org.parsakav.hotelbooking.model.mapper.PaymentRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    public PaymentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Payment save(Payment payment) {
        String sql = "INSERT INTO payments (booking_id, amount, payment_method) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, payment.getBookingId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentMethod().name());
            return ps;
        }, keyHolder);
        payment.setId(keyHolder.getKey().longValue());
        return payment;
    }

    @Override
    public int update(Payment payment) {
        if (payment.getId() == null) {
            throw new IllegalArgumentException("Payment ID must be provided for update.");
        }
        String sql = "UPDATE payments SET booking_id = ?, amount = ?, payment_method = ? WHERE id = ?";
        return jdbcTemplate.update(sql, payment.getBookingId(), payment.getAmount(), payment.getPaymentMethod().name(), payment.getId());
    }

    @Override
    public int delete(Payment payment) {
        if (payment.getId() == null) {
            throw new IllegalArgumentException("Payment ID must be provided for deletion.");
        }
        String sql = "DELETE FROM payments WHERE id = ?";
        return jdbcTemplate.update(sql, payment.getId());
    }

    @Override
    public Payment findById(long id) {
        String sql = "SELECT * FROM payments WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new PaymentRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseException("Payment not found",e);
        }
    }

    @Override
    public List<Payment> findAll() {
        String sql = "SELECT * FROM payments";
        return jdbcTemplate.query(sql, new PaymentRowMapper());
    }


}