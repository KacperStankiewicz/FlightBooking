package com.example.FlightBooking.DTO;

import com.example.FlightBooking.DTO.AirplaneDto;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class FlightDto implements Serializable {
    private final Integer id;
    private final String origin;
    private final String destination;
    private final Instant departureTime;
    private final Instant arrivalTime;
    private final Double price;
    private final AirplaneDto airplane;
}
