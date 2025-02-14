package org.parsakav.hotelbooking.service;

import org.parsakav.hotelbooking.dto.RoomDto;

import java.util.List;

public interface RoomService {
    public RoomDto createRoom(RoomDto roomDTO);

    public boolean updateRoom(RoomDto roomDTO);
    public RoomDto getAvailableRoom();
    public boolean deleteRoom(Long id);
        public RoomDto getRoomById(Long id);
        public List<RoomDto> getAllRooms();
}
