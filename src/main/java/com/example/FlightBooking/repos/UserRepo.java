package com.example.FlightBooking.repos;

import com.example.FlightBooking.DTO.BookingDto;
import com.example.FlightBooking.DTO.UserDto;
import com.example.FlightBooking.model.Booking;
import com.example.FlightBooking.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepo {
    private final EntityManager entityManager;

    public User getUserByID(int id){
        return (User) entityManager.createNativeQuery("SELECT * From user where user_id = ?", User.class)
                .setParameter(1,id)
                .getSingleResult();
    }

    public List<User> getAllUsers(){
        return (List<User>) entityManager.createNativeQuery("select * from user",User.class).getResultList();
    }

@Transactional
    public HttpStatus makeBooking(BookingDto bookingDto){
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery("insert into bookings(flight_id,user_id,seats_booked,num_of_seats)" +
                            " values (?,?,?,?)")
                    .setParameter(1,bookingDto.getFlight().getId())
                    .setParameter(2,bookingDto.getUser().getId())
                    .setParameter(3,bookingDto.getSeatsBooked())
                    .setParameter(4,bookingDto.getNumOfSeats())
                    .executeUpdate();
            return HttpStatus.OK;
        }catch (Exception e){
            return HttpStatus.NOT_MODIFIED;
        }
    }

    public List<Booking> getUserBookings(int id){
        return (List<Booking>) entityManager.createNativeQuery(
                "select * from bookings where user_id=?",Booking.class)
                .setParameter(1,id).getResultList();
    }

    @Transactional
    public HttpStatus deleteUserById(int id){
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery("delete from user where user_id=?")
                    .setParameter(1,id)
                    .executeUpdate();
            return HttpStatus.OK;
        }catch (Exception e){
            return HttpStatus.NOT_MODIFIED;
        }
    }

    @Transactional
    public HttpStatus updateUser(String query){
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery(query).executeUpdate();
            return HttpStatus.OK;
        }catch (Exception e){
            return HttpStatus.NOT_MODIFIED;
        }
    }
}
