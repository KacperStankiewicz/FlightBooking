package com.example.FlightBooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FlightDto {
    private Integer id;
    private String origin;
    private String destination;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private Double price;
    private AirplaneDto airplane;
}
