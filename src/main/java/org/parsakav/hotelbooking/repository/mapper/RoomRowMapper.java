package org.parsakav.hotelbooking.repository.mapper;


import org.parsakav.hotelbooking.model.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = new Room();
        room.setId(rs.getLong("id"));
        room.setType(Room.RoomType.valueOf(rs.getString("type")));
        room.setStatus(Room.RoomStatus.valueOf(rs.getString("status")));
        room.setPrice(rs.getDouble("price"));
        return room;
    }
}