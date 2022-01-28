package pl.pja.database.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AirplaneDto {
    @JsonProperty("airplane_id")
    private Integer id;
    @JsonProperty("model")
    private String model;
    @JsonProperty("seats")
    private Integer seats;
    @JsonProperty("plane_status")
    private String planeStatus;
}
