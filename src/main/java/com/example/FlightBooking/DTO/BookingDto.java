package com.example.FlightBooking.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookingDto implements Serializable {
    private final Integer id;
    private final FlightDto flight;
    private final UserDto user;
    private final String seatsBooked;
    private final Integer numOfSeats;
}
