package org.parsakav.hotelbooking.service;


import org.parsakav.hotelbooking.dto.BookingDTO;
import org.parsakav.hotelbooking.model.Room;

import java.util.List;

public interface BookingService {
        BookingDTO createBooking(BookingDTO bookingDTO);
        BookingDTO updateBooking(BookingDTO bookingDTO);
        void deleteBooking(Long id);
        BookingDTO getBookingById(Long id);
        List<BookingDTO> getAllBookings();

        // متدی برای ثبت رزرو با دریافت ایمیل مشتری و نوع اتاق
        BookingDTO bookRoom(String email, Room.RoomType roomType);
    }


