package pl.pja.backend.controllers;


import org.springframework.stereotype.Component;
import pl.pja.backend.services.FlightService;
import pl.pja.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pja.database.contracts.FlightDto;
import pl.pja.database.contracts.UserDto;

import java.util.List;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
@Component("backendAdminController")
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
    public ResponseEntity<HttpStatus> updateUser(UserDto userDto) {

        return ResponseEntity.status(userService.updateUser(userDto)).build();
    }

    @DeleteMapping("flight/delete")
    public ResponseEntity<HttpStatus> deleteFlight(@RequestParam int id) {
        return ResponseEntity.status(flightService.deleteFlight(id)).build();
    }

    @PutMapping("flight/update")
    public ResponseEntity<HttpStatus> updateFlight(@RequestBody FlightDto flightDto){
        return ResponseEntity.status(flightService.updateFlight(flightDto)).build();
    }
}
