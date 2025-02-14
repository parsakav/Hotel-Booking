package org.parsakav.hotelbooking.exceptions;

public class NoAvailableRoomException extends RuntimeException {
    public NoAvailableRoomException(String message) {
        super(message);
    }
}