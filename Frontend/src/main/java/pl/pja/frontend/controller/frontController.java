package pl.pja.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;



@Controller
public class frontController {
    final String API_URL="http://localhost:8080";
    WebClient webClient = WebClient.create(API_URL);

    @GetMapping("")
    public String homePage(){
        webClient.get().uri("").retrieve();

        return "homepage";
    }

    @GetMapping("flights")
    public String getFlights(Model model){
        return null;
    }
}
