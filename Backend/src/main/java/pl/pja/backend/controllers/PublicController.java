package pl.pja.backend.controllers;

import pl.pja.backend.DTO.UserDto;
import pl.pja.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class PublicController {
    private final UserService userService;


    @PostMapping("register")
    public ResponseEntity register(@RequestBody UserDto userDto){
        return ResponseEntity.status(userService.registerUser(userDto)).build();
    }

}
