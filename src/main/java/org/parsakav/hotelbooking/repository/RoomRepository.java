package org.parsakav.hotelbooking.repository;

import org.parsakav.hotelbooking.model.Room;

public interface RoomRepository extends Crud<Room> {
    Room save(Room room);
    int delete(Room room);
    java.util.List<Room> findAll();
    Room findById(long id);
    int update(Room room);
    Room findAvailableRoom();
}
