package com.example.FlightBooking.DTO;

import com.example.FlightBooking.DTO.FlightDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookingDto implements Serializable {
    private final Integer id;
    private final FlightDto flight;
    private final Integer seatsBooked;
}
