package pl.pja.backend.mappers;

import pl.pja.backend.DTO.AirplaneDto;
import pl.pja.backend.DTO.BookingDto;
import pl.pja.backend.DTO.FlightDto;
import pl.pja.backend.DTO.UserDto;
import pl.pja.backend.model.Airplane;
import pl.pja.backend.model.Booking;
import pl.pja.backend.model.Flight;
import pl.pja.backend.model.User;
import org.springframework.stereotype.Component;


@Component
public class DtoMapper {


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
