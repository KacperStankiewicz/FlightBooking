package com.example.FlightBooking.controllers;

import com.example.FlightBooking.DTO.BookingDto;
import com.example.FlightBooking.repos.UserRepo;
import com.example.FlightBooking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity userPage(){
        return ResponseEntity.ok("<p>Hello user<p>");
    }

    @PostMapping("/make/booking")
    public ResponseEntity userAddBooking(@RequestBody BookingDto booking){
        return userService.makeBooking(booking);
    }
}
