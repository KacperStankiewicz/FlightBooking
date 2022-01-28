package pl.pja.frontend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import pl.pja.database.contracts.BookingDto;
import pl.pja.database.contracts.FlightDto;
import pl.pja.database.contracts.UserDto;
import pl.pja.database.model.User;
import pl.pja.frontend.securityConfig.MyUserPrincipal;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class FrontController {
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


    @GetMapping("login")
    public String loginRedirect(){
        return "loginPage";
    }

    @GetMapping("registration")
    public String register(Model model){
        model.addAttribute("newUser",new UserDto());
        return "register";
    }

    @PostMapping("registration")
    public String registerUser(@ModelAttribute UserDto userDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        userDto.setPassword(encoder.encode(userDto.getPassword()));

        webClient.post().uri("register")
                .body(Mono.just(userDto),UserDto.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return "redirect:/login";
    }

    @GetMapping("makeBooking")
    public String redirectToBookingForm(@RequestParam int flight_id, @AuthenticationPrincipal MyUserPrincipal user, Model model){
        model.addAttribute("flight_id",flight_id);
        model.addAttribute("booking",new BookingDto());
        model.addAttribute("user",user);
        return "bookingForm";
    }

    @PostMapping("post/make/booking")
    public String makeBooking(@ModelAttribute BookingDto booking, @RequestParam int flight_id, @RequestParam int user_id){
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

    @GetMapping("get/bookings")
    public String showUserBookings(Model model, @AuthenticationPrincipal MyUserPrincipal user){
        BookingDto[] bookingDtos = webClient.get()
                .uri("user/bookings?id="+user.getId())
                .retrieve()
                .bodyToMono(BookingDto[].class)
                .block();
        List<BookingDto> b = Arrays.asList(bookingDtos);

        model.addAttribute("bookings",b);

        return "userBookings";
    }

    @GetMapping("updateUser")
    public String redirectUpdateUser(@RequestParam int id, Model model){
        UserDto userDto = new UserDto();
        userDto.setId(id);
        model.addAttribute("user",userDto);
        return "userUpdateForm";
    }

    @PostMapping("updateUser")
    public String updateuser(@ModelAttribute UserDto user){
        webClient.put()
                .uri("admin/user/update")
                .body(Mono.just(user),UserDto.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return "redirect:userInfo";
    }

    @GetMapping("deleteUser")
    public String deleteUser(@RequestParam int user_id){
        webClient.delete()
                .uri("admin/user/delete?id="+user_id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return "redirect:userInfo";
    }

    @GetMapping("deleteFlight")
    public String deleteFlight(@RequestParam int flight_id){
        webClient.delete()
                .uri("admin/flight/delete?id="+flight_id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return "redirect:flights";
    }

    @GetMapping("updateFlight")
    public String redirectUpdateFlight(@RequestParam int id, Model model){
        FlightDto flightDto = new FlightDto();
        flightDto.setId(id);
        model.addAttribute("flight",flightDto);
        return "flightUpdateForm";
    }

    @PostMapping("updateFlight")
    public String updateFlight(@ModelAttribute FlightDto flightDto){
        webClient.put()
                .uri("admin/flight/update")
                .body(Mono.just(flightDto),FlightDto.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return "redirect:flights";
    }

    @GetMapping("userInfo")
    public String getUsers(Model model){
        UserDto[] users = webClient.get()
                .uri("user/info")
                .retrieve()
                .bodyToMono(UserDto[].class)
                .block();

        List<UserDto> u = List.of(users);
        model.addAttribute("users",u);

        return "userinfo";
    }

}

