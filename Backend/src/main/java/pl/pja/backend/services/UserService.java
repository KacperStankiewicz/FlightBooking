package pl.pja.backend.services;

import pl.pja.backend.DTO.BookingDto;
import pl.pja.backend.DTO.UserDto;
import pl.pja.backend.mappers.DtoMapper;
import pl.pja.backend.repos.FlightRepo;
import pl.pja.backend.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final FlightRepo flightRepo;
    private final DtoMapper dtoMapper;

    private String makeSearchQuery(UserDto u){
        String str = "";
        if(u.getFullName() != null && !Objects.equals(u.getFullName(), "")){
            str+="full_name='"+u.getFullName()+ "' and ";
        }

        if(u.getPhone() != null && !Objects.equals(u.getPhone(), "")){
            str+="phone='"+u.getPhone()+ "' and ";
        }

        if(u.getCity() != null && !Objects.equals(u.getCity(), "")){
            str+="city='"+u.getCity()+ "' and ";
        }

        if(u.getStreet() != null && !Objects.equals(u.getStreet(), "")){
            str+="street='"+u.getStreet()+ "' and ";
        }

        if(u.getPostalCode() != null && !Objects.equals(u.getPostalCode(), "")){
            str+="postal_code='"+u.getPostalCode()+ "' and ";
        }

        if(u.getEmail() != null && !Objects.equals(u.getEmail(), "")){
            str+="email='"+u.getEmail()+ "' and ";
        }

        if(u.getUserRole() != null && !Objects.equals(u.getUserRole(), "")){
            str+="user_role='"+u.getUserRole()+ "' and ";
        }

        if(!str.equals("")){
            return "where "+str+"true";
        }else return str;
    }

    private String makeUpdateQuery(UserDto u){
        String str = "";
        if(u.getFullName() != null && !Objects.equals(u.getFullName(), "")){
            str+="full_name='"+u.getFullName()+ "',";
        }

        if(u.getPhone() != null && !Objects.equals(u.getPhone(), "")){
            str+="phone='"+u.getPhone()+ "',";
        }

        if(u.getCity() != null && !Objects.equals(u.getCity(), "")){
            str+="city='"+u.getCity()+ "',";
        }

        if(u.getStreet() != null && !Objects.equals(u.getStreet(), "")){
            str+="street='"+u.getStreet()+ "',";
        }

        if(u.getPostalCode() != null && !Objects.equals(u.getPostalCode(), "")){
            str+="postal_code='"+u.getPostalCode()+ "',";
        }

        if(u.getEmail() != null && !Objects.equals(u.getEmail(), "")){
            str+="email='"+u.getEmail()+ "',";
        }

        if(u.getUserRole() != null && !Objects.equals(u.getUserRole(), "")){
            str+="user_role='"+u.getUserRole()+ "',";
        }

        if(!str.equals("")){
            return str.substring(0,str.length()-1);
        }else return str;
    }


    public HttpStatus makeBooking(BookingDto bookingDto){

       return userRepo.makeBooking(makeProperBookingDtoObject(bookingDto));
    }

    private BookingDto makeProperBookingDtoObject(BookingDto b) {
        if(b.getFlight() == null){
            b.setFlight(dtoMapper.mapToFlightDto(flightRepo.getFlightById(b.getFlight_id())));
        }
        if(b.getUser() == null){
            b.setUser(dtoMapper.mapToUserDto(userRepo.getUserByID(b.getUser_id())));
        }
        return b;
    }

    public List<UserDto> userInfo(int id){
        if(id==0){
            return userRepo.getAllUsers().stream().map(dtoMapper::mapToUserDto).collect(Collectors.toList());
        }

        return List.of(dtoMapper.mapToUserDto(userRepo.getUserByID(id)));
    }

    public List<BookingDto> userBookings(int id){
        return userRepo.getUserBookings(id).stream().map(dtoMapper::mapToBookingDto).collect(Collectors.toList());
    }

    public HttpStatus deleteUser(int id){
        return userRepo.deleteUserById(id);
    }

    public HttpStatus updateUser(UserDto userDto){
        return userRepo.updateUser(makeUpdateQuery(userDto),userDto.getId());
    }

    public HttpStatus registerUser(UserDto userDto){
        return userRepo.registerUser(userDto);
    }
}
