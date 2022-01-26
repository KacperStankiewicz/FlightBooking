package pl.pja.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import pl.pja.backend.DTO.BookingDto;
import pl.pja.backend.DTO.FlightDto;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class frontController {
    final String API_URL = "http://localhost:8080";
    WebClient webClient = WebClient.create(API_URL);

    @GetMapping("")
    public String homePage() {
        webClient.get().uri("").retrieve();

        return "homepage";
    }

    @GetMapping("homepage")
    public String redirectToHomePage(){
        return "redirect:";
    }

    @GetMapping("flights")
    public String getFlights(Model model) {
        FlightDto[] flights = webClient.get()
                .uri("/user/flights")
                .retrieve()
                .bodyToMono(FlightDto[].class)
                .block();
        List<FlightDto> flightDtoList = Arrays.asList(flights);

        model.addAttribute("flights",flightDtoList);

        return "flights";
    }

    @GetMapping("makeBooking/{flight_id}")
    public String redirectToBookingForm(@PathVariable int flight_id, //@AuthenticationPrincipal MyUserPrincipal user,
                                        Model model){
        model.addAttribute("flight_id",flight_id);
        model.addAttribute("booking",new BookingDto());
//        model.addAttribute("user",user);
        return "bookingForm";
    }

    @PostMapping("make/booking")
    public String makeBooking(@ModelAttribute BookingDto booking, @RequestParam int flight_id,@RequestParam int user_id){
        booking.setFlight_id(flight_id);
        booking.setUser_id(user_id);
        webClient
                .post()
                .uri("user/make/booking")
                .body(Mono.just(booking),BookingDto.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return "afterBooking";
    }
}

