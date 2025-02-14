package org.parsakav.hotelbooking.repository;

import org.parsakav.hotelbooking.exceptions.DatabaseException;
import org.parsakav.hotelbooking.exceptions.NotIdProvidedException;
import org.parsakav.hotelbooking.model.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {


        private final JdbcTemplate jdbcTemplate;

        public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

    public Customer save(Customer customer) {
        String sql = "INSERT INTO customers (name, email, phonenumber, address) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPhoneNumber());
            ps.setString(4, customer.getAddress());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            customer.setId(keyHolder.getKey().longValue());
        }

        return customer;
    }

    @Override
    public int delete(Customer customer) {
        StringBuilder sql = new StringBuilder("DELETE FROM customers");
        List<Object> params = new ArrayList<>();

        if (customer.getId() != null) {
            sql.append(" WHERE id = ?");
            params.add(customer.getId());
        }
        if (customer.getPhoneNumber() != null) {
            sql.append(params.isEmpty() ? " WHERE" : " OR").append(" phonenumber = ?");
            params.add(customer.getPhoneNumber());
        }
        if (customer.getEmail() != null) {
            sql.append(params.isEmpty() ? " WHERE" : " OR").append(" email = ?");
            params.add(customer.getAddress());
        }

        if (params.isEmpty()) {
            throw new IllegalArgumentException("At least one condition must be provided for deletion.");
        }

        System.out.println(sql);
        return jdbcTemplate.update(sql.toString(), params.toArray());
    }

    public List<Customer> findAll() {
            String sql = "SELECT * FROM customers";
            return jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(Customer.class));
        }

    @Override
    public Customer findById(long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class))).get();
        } catch (EmptyResultDataAccessException e) {
           throw new DatabaseException("Customer with id :"+id+" not found",e);
        }
    }
    @Override
    public int update(Customer customer) {
        StringBuilder sql = new StringBuilder("UPDATE customers SET");
        List<Object> params = new ArrayList<>();

        if (customer.getName() != null) {
            sql.append(" name = ?,");
            params.add(customer.getName());
        }
        if (customer.getEmail() != null) {
            sql.append(" email = ?,");
            params.add(customer.getEmail());
        }
        if (customer.getPhoneNumber() != null) {
            sql.append(" phonenumber = ?,");
            params.add(customer.getPhoneNumber());
        }
        if (customer.getAddress() != null) {
            sql.append(" address = ?,");
            params.add(customer.getAddress());
        }

        if (params.isEmpty()) {
            throw new IllegalArgumentException("At least one field must be provided for update.");
        }

        sql.deleteCharAt(sql.length() - 1);

        if (customer.getId() == null) {
            throw new NotIdProvidedException("Customer ID must be provided for update.");
        }
        if(customer.getId()!=null) {
            sql.append(" WHERE id = ?");
            params.add(customer.getId());
        }
        return jdbcTemplate.update(sql.toString(), params.toArray());
    }

    @Override
    public Customer findByEmail(String email) {
       String sql = "SELECT * FROM customers WHERE email = ?";
            try {
                return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(Customer.class))).get();
            } catch (EmptyResultDataAccessException e) {
                throw new DatabaseException("Customer with email :"+email+" not found",e);
            }

}

    @Override
    public Customer findByPhoneNumber(String number) {
        String sql = "SELECT * FROM customers WHERE phonenumber = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{number}, new BeanPropertyRowMapper<>(Customer.class))).get();
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseException("Customer with phoneNumber :"+number+" not found",e);
        }
    }
}

