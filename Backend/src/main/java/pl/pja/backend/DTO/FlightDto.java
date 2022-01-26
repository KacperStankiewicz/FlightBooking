package pl.pja.backend.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FlightDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("departureTime")
    private Timestamp departureTime;
    @JsonProperty("arrivalTime")
    private Timestamp arrivalTime;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("airplane")
    private AirplaneDto airplane;

//    public boolean isNull(){
//        List<Field> fields = Arrays.stream(this.getClass().getFields()).collect(Collectors.toList());
//        for (Field f:fields) {
//            if(f.toString() == null || f.toString().equals("")){
//                return true;
//            }
//        }
//        return false;
//    }
}
