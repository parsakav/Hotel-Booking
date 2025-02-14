package org.parsakav.hotelbooking.service;


import org.parsakav.hotelbooking.dto.RoomDto;
import org.parsakav.hotelbooking.model.Room;
import org.parsakav.hotelbooking.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }





    @Override
    public RoomDto createRoom(RoomDto roomDTO) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDTO,room);

        Room saved = roomRepository.save(room);
        BeanUtils.copyProperties(roomDTO,saved);
        return roomDTO;
    }

    public boolean updateRoom(RoomDto roomDTO) {
        if (roomDTO.getId() == null) {
            throw new IllegalArgumentException("Room ID must be provided for update.");
        }
        Room room = new Room();
        int update = roomRepository.update(room);

        return update>0;
    }

    @Override
    public boolean deleteRoom(Long id) {
        Room room = roomRepository.findById(id);
        if (room == null) {
            throw new RuntimeException("Room not found with id: " + id);
        }
       return roomRepository.delete(room)>0;
    }

    @Override
    public RoomDto getRoomById(Long id) {
        Room room = roomRepository.findById(id);
        if (room == null) {
            throw new RuntimeException("Room not found with id: " + id);
        }
        RoomDto roomDto = new RoomDto();
        BeanUtils.copyProperties(room,roomDto);
        return roomDto;
    }

    @Override
    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(e->{
                    RoomDto roomDto = new RoomDto();
                    BeanUtils.copyProperties(e,roomDto);
                    return roomDto;
                })
                .collect(Collectors.toList());
    }

    public RoomDto getAvailableRoom() {
        Room room = roomRepository.findAvailableRoom();
        RoomDto roomDto = new RoomDto();
        BeanUtils.copyProperties(room,roomDto);
        return roomDto;
    }
}