package com.example.FlightBooking.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Integer id;
    private final String fullName;
    private final String phone;
    private final String city;
    private final String street;
    private final String postalCode;
    private final String email;
    private final String userRole;
}
