package com.example.FlightBooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AirplaneDto {
    private Integer id;
    private String model;
    private Integer seats;
    private String planeStatus;
}
