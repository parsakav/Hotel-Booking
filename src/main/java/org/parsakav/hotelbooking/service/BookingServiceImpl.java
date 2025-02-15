package org.parsakav.hotelbooking.service;



import org.parsakav.hotelbooking.dto.BookingDTO;
import org.parsakav.hotelbooking.exceptions.NoAvailableRoomException;
import org.parsakav.hotelbooking.model.Booking;
import org.parsakav.hotelbooking.model.Customer;
import org.parsakav.hotelbooking.model.Room;
import org.parsakav.hotelbooking.repository.BookingRepository;
import org.parsakav.hotelbooking.repository.CustomerRepository;
import org.parsakav.hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              CustomerRepository customerRepository,
                              RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    private BookingDTO convertToDTO(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getCustomerId(),
                booking.getRoomId(),
                booking.getBookingDate(),
                booking.getCheckInDate(),
                booking.getCheckOutDate()
        );
    }

    private Booking convertToEntity(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setCustomerId(dto.getCustomerId());
        booking.setRoomId(dto.getRoomId());
        booking.setBookingDate(dto.getBookingDate());
        booking.setCheckInDate(dto.getCheckInDate());
        booking.setCheckOutDate(dto.getCheckOutDate());
        return booking;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        Booking saved = bookingRepository.save(booking);
        return convertToDTO(saved);
    }

    @Override
    public BookingDTO updateBooking(BookingDTO bookingDTO) {
        if (bookingDTO.getId() == null) {
            throw new IllegalArgumentException("Booking ID must be provided for update.");
        }
        Booking booking = convertToEntity(bookingDTO);
        bookingRepository.update(booking);
        Booking updated = bookingRepository.findById(booking.getId());
        if (updated == null) {
            throw new RuntimeException("Booking not found with id: " + booking.getId());
        }
        return convertToDTO(updated);
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        bookingRepository.delete(booking);
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        return convertToDTO(booking);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookingDTO bookRoom(String email, Room.RoomType roomType) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new RuntimeException("Customer not found with email: " + email);
        }

        Room room = roomRepository.findAvailableRoomByType(roomType);
        if (room == null) {
            throw new NoAvailableRoomException("No available room found for type: " + roomType);
        }

        room.setStatus(Room.RoomStatus.RESERVED);
        roomRepository.update(room);

        Booking booking = new Booking();
        booking.setCustomerId(customer.getId());
        booking.setRoomId(room.getId());
        booking.setBookingDate(LocalDate.now());
        booking.setCheckInDate(LocalDate.now());
        booking.setCheckOutDate(LocalDate.now().plusDays(1));

        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

}