package org.parsakav.hotelbooking.model;

public class Room {
    public enum RoomType { STANDARD, LUX, SWEET }
    public enum RoomStatus { RESERVED, AVAILABLE }

    private Long id;
    private RoomType type;
    private RoomStatus status;
    private double price;

    // Constructors
    public Room() {}

    public Room(Long id, RoomType type, RoomStatus status, double price) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public RoomType getType() { return type; }
    public void setType(RoomType type) { this.type = type; }

    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
