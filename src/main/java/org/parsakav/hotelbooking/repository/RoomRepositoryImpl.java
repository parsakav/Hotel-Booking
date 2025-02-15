package org.parsakav.hotelbooking.repository;

import org.parsakav.hotelbooking.exceptions.DatabaseException;
import org.parsakav.hotelbooking.exceptions.NoAvailableRoomException;
import org.parsakav.hotelbooking.exceptions.NotIdProvidedException;
import org.parsakav.hotelbooking.model.Room;
import org.parsakav.hotelbooking.repository.mapper.RoomRowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SimplePropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoomRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Room save(Room room) {
        String sql = "INSERT INTO rooms (type, status, price) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, room.getType().name());
            ps.setString(2, room.getStatus().name());
            ps.setDouble(3, room.getPrice());
            return ps;
        }, keyHolder);
        room.setId(keyHolder.getKey().longValue());
        return room;
    }

    @Override
    public int delete(Room room) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        return jdbcTemplate.update(sql, room.getId());
    }

    @Override
    public List<Room> findAll() {
        String sql = "SELECT * FROM rooms";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Room.class));
    }

    @Override
    public Room findById(long id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Room.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int update(Room room) {
        if (room.getId() == null) {
            throw new NotIdProvidedException("Room ID must be provided for update.");
        }
        String sql = "UPDATE rooms SET type = ?, status = ?, price = ? WHERE id = ?";
        return jdbcTemplate.update(sql, room.getType().name(), room.getStatus().name(), room.getPrice(), room.getId());
    }

    @Override
    public Room findAvailableRoom() {
        String sql = "SELECT * FROM rooms WHERE status = ? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, new RoomRowMapper(), Room.RoomStatus.AVAILABLE.name());
        } catch (EmptyResultDataAccessException e) {
            throw new NoAvailableRoomException("No available room found.");
        }
    }
    @Override
    public Room findAvailableRoomByType(Room.RoomType type) {
        String sql = "SELECT * FROM rooms WHERE status = ? AND type = ? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, new RoomRowMapper(),
                    Room.RoomStatus.AVAILABLE.name(), type.name());
        } catch (EmptyResultDataAccessException e) {
            throw new DatabaseException("There is not availale room ",e);
        }
    }
}