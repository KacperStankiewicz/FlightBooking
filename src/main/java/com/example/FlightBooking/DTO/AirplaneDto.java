package com.example.FlightBooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AirplaneDto {
    private Integer id;
    private String model;
    private Integer seats;
    private String planeStatus;
}
