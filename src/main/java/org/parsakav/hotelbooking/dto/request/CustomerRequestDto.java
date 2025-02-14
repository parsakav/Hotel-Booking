package org.parsakav.hotelbooking.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRequestDto {
    @JsonProperty(required = false)
    private long id;
    @NotBlank(message = "name must be not blank")
    private String name;
    @NotBlank(message = "email must be not blank")

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
    private String email;
    @NotBlank(message = "phoneNumber must be not blank")

    @Pattern(regexp = "^[0-9+()-]{6,20}$",message = "Invalid phoneNumber format")
    private String phoneNumber;
    @NotBlank(message = "address must be not blank")

    private String address;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
