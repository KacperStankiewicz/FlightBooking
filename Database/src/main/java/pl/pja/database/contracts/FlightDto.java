package pl.pja.database.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

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
