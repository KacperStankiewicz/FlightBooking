package com.example.FlightBooking.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PoblicController {

    @GetMapping("")
    public ResponseEntity homePage(){
        return ResponseEntity.ok("<p>Hello<p>");
    }

}
