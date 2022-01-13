package com.example.FlightBooking.controllers;

import com.example.FlightBooking.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    @GetMapping("")
    public ResponseEntity userPage(){
        return ResponseEntity.ok("<p>Hello user<p>");
    }
}
