package pl.pja.database.mappers;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.pja.database.contracts.AirplaneDto;
import pl.pja.database.contracts.BookingDto;
import pl.pja.database.contracts.FlightDto;
import pl.pja.database.contracts.UserDto;
import pl.pja.database.model.Airplane;
import pl.pja.database.model.Booking;
import pl.pja.database.model.Flight;
import pl.pja.database.model.User;


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
