package org.parsakav.hotelbooking.controller;

import org.parsakav.hotelbooking.dto.RoomDto;
import org.parsakav.hotelbooking.model.Booking;
import org.parsakav.hotelbooking.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@Valid @RequestBody RoomDto roomDTO) {
        RoomDto created = roomService.createRoom(roomDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateRoom(@Valid @RequestBody RoomDto roomDTO) {
        boolean updated = roomService.updateRoom(roomDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRoom(@PathVariable Long id) {
        boolean b = roomService.deleteRoom(id);
        return new ResponseEntity<>(b,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable Long id) {
        RoomDto roomDTO = roomService.getRoomById(id);
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        List<RoomDto> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    @GetMapping("/available")
    public ResponseEntity<RoomDto> getAvailableRoom() {
        RoomDto roomDTO = roomService.getAvailableRoom();
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }
}