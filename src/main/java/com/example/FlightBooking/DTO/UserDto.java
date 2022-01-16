package com.example.FlightBooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String fullName;
    private String phone;
    private String city;
    private String street;
    private String postalCode;
    private String email;
    private String userRole;
}
