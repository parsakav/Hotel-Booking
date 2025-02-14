package org.parsakav.hotelbooking.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.parsakav.hotelbooking.model.Room;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class RoomDto {

    @JsonProperty(required = false)
    private Long id;

    @NotNull(message = "Room type must be provided")
    private Room.RoomType type;

    @NotNull(message = "Room status must be provided")
    private Room.RoomStatus status;

    @Positive(message = "Price must be positive")
    private double price;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room.RoomType getType() {
        return type;
    }

    public void setType(Room.RoomType type) {
        this.type = type;
    }

    public Room.RoomStatus getStatus() {
        return status;
    }

    public void setStatus(Room.RoomStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}