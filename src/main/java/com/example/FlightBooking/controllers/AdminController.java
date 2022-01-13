package com.example.FlightBooking.controllers;

import com.example.FlightBooking.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepo userRepo;

    @GetMapping("{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id){
        return ResponseEntity.ok(userRepo.getUserByID(id));
    }
}
