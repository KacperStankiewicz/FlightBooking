package com.example.FlightBooking.services;

import com.example.FlightBooking.DTO.BookingDto;
import com.example.FlightBooking.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public ResponseEntity makeBooking(BookingDto bookingDto){
       return userRepo.makeBooking(bookingDto);
    }
}
