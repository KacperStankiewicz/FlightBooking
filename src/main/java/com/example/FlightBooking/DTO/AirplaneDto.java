package com.example.FlightBooking.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class AirplaneDto implements Serializable {
    private final Integer id;
    private final String model;
    private final Integer seats;
    private final String planeStatus;
}
