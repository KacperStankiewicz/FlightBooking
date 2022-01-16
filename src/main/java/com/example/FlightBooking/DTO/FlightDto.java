package com.example.FlightBooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class FlightDto {
    private Integer id;
    private String origin;
    private String destination;
    private Instant departureTime;
    private Instant arrivalTime;
    private Double price;
    private AirplaneDto airplane;
}
