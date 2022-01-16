package com.example.FlightBooking.DTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BookingDto implements Serializable {
    private FlightDto flight;
    private UserDto user;
    private String seatsBooked;
    private Integer numOfSeats;
}
