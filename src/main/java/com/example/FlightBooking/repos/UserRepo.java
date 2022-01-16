package com.example.FlightBooking.repos;

import com.example.FlightBooking.DTO.BookingDto;
import com.example.FlightBooking.model.Booking;
import com.example.FlightBooking.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepo {
    private final EntityManager entityManager;

    public User getUserByID(int id){
        return (User) entityManager.createNativeQuery("SELECT * From user where user_id = ?", User.class)
                .setParameter(1,id)
                .getSingleResult();
    }

@Transactional
    public ResponseEntity makeBooking(BookingDto bookingDto){
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery("insert into bookings(flight_id,user_id,seats_booked,num_of_seats)" +
                            " values (?,?,?,?)")
                    .setParameter(1,bookingDto.getFlight().getId())
                    .setParameter(2,bookingDto.getUser().getId())
                    .setParameter(3,bookingDto.getSeatsBooked())
                    .setParameter(4,bookingDto.getNumOfSeats())
                    .executeUpdate();
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

    }
}
