package com.example.FlightBooking.controllers;

import com.example.FlightBooking.DTO.UserDto;
import com.example.FlightBooking.services.FlightService;
import com.example.FlightBooking.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final FlightService flightService;


    @GetMapping("/user/info")
    public ResponseEntity<List<UserDto>> getUser(@RequestParam(required = false, defaultValue = "0") int id) {
        return ResponseEntity.ok(userService.userInfo(id));
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam int id) {
        return ResponseEntity.status(userService.deleteUser(id)).build();
    }

    @PutMapping("/user/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestParam(required = false) String full_name,
                                                 @RequestParam(required = false) String phone,
                                                 @RequestParam(required = false) String city,
                                                 @RequestParam(required = false) String street,
                                                 @RequestParam(required = false) String postal_code,
                                                 @RequestParam(required = false) String email,
                                                 @RequestParam(required = false) String user_role) {
        UserDto user = UserDto.builder()
                .fullName(full_name)
                .userRole(user_role)
                .email(email)
                .postalCode(postal_code)
                .phone(phone)
                .street(street)
                .city(city)
                .build();

        return ResponseEntity.status(userService.updateUser(user)).build();
    }

    @DeleteMapping("flight/delete")
    public ResponseEntity<HttpStatus> deleteFlight(@RequestParam int id) {
        return ResponseEntity.status(flightService.deleteFlight(id)).build();
    }
}
