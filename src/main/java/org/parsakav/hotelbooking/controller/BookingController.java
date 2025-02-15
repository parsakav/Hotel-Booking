package org.parsakav.hotelbooking.controller;

import org.parsakav.hotelbooking.dto.BookingDTO;
import org.parsakav.hotelbooking.model.Room;
import org.parsakav.hotelbooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        BookingDTO created = bookingService.createBooking(bookingDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BookingDTO> updateBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        BookingDTO updated = bookingService.updateBooking(bookingDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable Long id) {
        BookingDTO bookingDTO = bookingService.getBookingById(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<BookingDTO> bookRoom(@RequestParam String email,
                                               @RequestParam Room.RoomType roomType) {
        BookingDTO bookingDTO = bookingService.bookRoom(email, roomType);
        return new ResponseEntity<>(bookingDTO, HttpStatus.CREATED);
    }
}