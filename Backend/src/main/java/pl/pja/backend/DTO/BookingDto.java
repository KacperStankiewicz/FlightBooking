package pl.pja.backend.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BookingDto {
    @JsonProperty("flight")
    private FlightDto flight;
    @JsonProperty("user")
    private UserDto user;
    @JsonProperty("seats_booked")
    private String seatsBooked;
    @JsonProperty("num_of_seats")
    private Integer numOfSeats;
    @JsonProperty("flight_id")
    private int flight_id;
    @JsonProperty("user_id")
    private int user_id;
}
