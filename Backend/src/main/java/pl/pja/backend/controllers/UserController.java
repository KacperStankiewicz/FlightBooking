package pl.pja.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.pja.backend.services.FlightService;
import pl.pja.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pja.database.contracts.BookingDto;
import pl.pja.database.contracts.FlightDto;
import pl.pja.database.contracts.UserDto;

import java.sql.Timestamp;


@Controller
@RequiredArgsConstructor
@RequestMapping("user")
@Component("backendUserController")
public class UserController {

    private final UserService userService;
    private final FlightService flightService;

    @GetMapping("")
    public ResponseEntity userPage() {
        return ResponseEntity.ok("<p>Hello user<p>");
    }

    @PostMapping("/make/booking")
    public ResponseEntity userAddBooking(@RequestBody BookingDto booking) {
        return ResponseEntity.status(userService.makeBooking(booking)).build();
    }


    @GetMapping("/bookings")
    public ResponseEntity userBookings(@RequestParam int id){
        return ResponseEntity.ok(userService.userBookings(id));
    }

    @GetMapping("/info")
    public ResponseEntity userInfo(@RequestParam int id) {
        return ResponseEntity.ok(userService.userInfo(id));
    }

    @GetMapping("/flights")
    public ResponseEntity getFlights(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(required = false) String origin,
                                     @RequestParam(required = false) String destination,
                                     @RequestParam(required = false) Timestamp departureTime,
                                     @RequestParam(required = false) Timestamp arrivalTime,
                                     @RequestParam(required = false) Double price) {
        FlightDto flight = FlightDto.builder()
                .origin(origin)
                .destination(destination)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .price(price)
                .build();

        return new ResponseEntity<>(flightService.getFlightsByPage(page,flight), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity registerNewUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(userService.registerUser(userDto)).build();
    }
}
