package com.example.FlightBooking.DTO;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
public class BookingDto {
    private FlightDto flight;
    private UserDto user;
    private String seatsBooked;
    private Integer numOfSeats;
}
