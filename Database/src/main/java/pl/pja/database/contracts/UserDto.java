package pl.pja.database.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("fullName")
    private String fullName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("city")
    private String city;
    @JsonProperty("street")
    private String street;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("email")
    private String email;
    @JsonProperty("userRole")
    private String userRole;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;


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
