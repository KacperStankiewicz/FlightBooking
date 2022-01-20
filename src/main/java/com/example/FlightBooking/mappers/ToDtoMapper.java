package com.example.FlightBooking.mappers;

import com.example.FlightBooking.DTO.AirplaneDto;
import com.example.FlightBooking.DTO.BookingDto;
import com.example.FlightBooking.DTO.FlightDto;
import com.example.FlightBooking.DTO.UserDto;
import com.example.FlightBooking.model.Airplane;
import com.example.FlightBooking.model.Booking;
import com.example.FlightBooking.model.Flight;
import com.example.FlightBooking.model.User;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class ToDtoMapper {


    public UserDto mapToUserDto(User u){
        return UserDto.builder()
                .id(u.getId())
                .fullName(u.getFullName())
                .phone(u.getPhone())
                .city(u.getCity())
                .street(u.getStreet())
                .postalCode(u.getPostalCode())
                .email(u.getEmail())
                .userRole(u.getUserRole())
                .build();
    }

    public AirplaneDto mapToAirplaneDto(Airplane a){
        return AirplaneDto.builder()
                .model(a.getModel())
                .id(a.getId())
                .planeStatus(a.getPlaneStatus())
                .seats(a.getSeats())
                .build();
    }

    public FlightDto mapToFlightDto(Flight f){
        return FlightDto.builder()
                .origin(f.getOrigin())
                .destination(f.getDestination())
                .departureTime(f.getDepartureTime())
                .arrivalTime(f.getArrivalTime())
                .price(f.getPrice())
                .id(f.getId())
                .airplane(mapToAirplaneDto(f.getAirplane()))
                .build();
    }

    public BookingDto mapToBookingDto(Booking b){
        return BookingDto.builder()
                .user(mapToUserDto(b.getUser()))
                .flight(mapToFlightDto(b.getFlight()))
                .numOfSeats(b.getNumOfSeats())
                .seatsBooked(b.getSeatsBooked())
                .build();
    }
}
