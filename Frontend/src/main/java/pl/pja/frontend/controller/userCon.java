//package pl.pja.frontend.controller;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.function.client.WebClient;
//import pl.pja.database.contracts.BookingDto;
//import pl.pja.frontend.securityConfig.MyUserPrincipal;
//import reactor.core.publisher.Mono;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Controller
//public class userCon {
//    final String API_URL = "http://localhost:8080";
//    WebClient webClient = WebClient.create(API_URL);
//
//    @GetMapping("user/makeBooking")
//    public String redirectToBookingForm(@RequestParam int flight_id, @AuthenticationPrincipal MyUserPrincipal user, Model model){
//        model.addAttribute("flight_id",flight_id);
//        model.addAttribute("booking",new BookingDto());
//        model.addAttribute("user",user);
//        return "bookingForm";
//    }
//
//    @PostMapping("user/post/make/booking")
//    public String makeBooking(@ModelAttribute BookingDto booking, @RequestParam int flight_id, @RequestParam int user_id){
//        booking.setFlight_id(flight_id);
//        booking.setUser_id(user_id);
//        webClient
//                .post()
//                .uri("user/make/booking")
//                .body(Mono.just(booking),BookingDto.class)
//                .retrieve()
//                .bodyToMono(Void.class)
//                .block();
//        return "afterBooking";
//    }
//
//    @GetMapping("user/get/bookings")
//    public String showUserBookings(Model model, @AuthenticationPrincipal MyUserPrincipal user){
//        BookingDto[] bookingDtos = webClient.get()
//                .uri("user/bookings?id="+user.getId())
//                .retrieve()
//                .bodyToMono(BookingDto[].class)
//                .block();
//        List<BookingDto> b = Arrays.asList(bookingDtos);
//
//        model.addAttribute("bookings",b);
//
//        return "userBookings";
//    }
//}
