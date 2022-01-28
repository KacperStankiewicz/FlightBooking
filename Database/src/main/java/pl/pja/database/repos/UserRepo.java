package pl.pja.database.repos;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.pja.database.contracts.BookingDto;
import pl.pja.database.contracts.UserDto;
import pl.pja.database.mappers.DtoMapper;
import pl.pja.database.model.Booking;
import pl.pja.database.model.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Component
public class UserRepo {
    private final EntityManager entityManager;

    @Autowired
    UserRepo(EntityManager entityManager){
        this.entityManager=entityManager;
    }

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
                    .setParameter(1,bookingDto.getFlight_id())
                    .setParameter(2,bookingDto.getUser_id())
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
    public HttpStatus updateUser(String query,int id){
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery("update user set "+query+" where user_id=?")
                    .setParameter(1,id)
                    .executeUpdate();
            return HttpStatus.OK;
        }catch (Exception e){
            return HttpStatus.NOT_MODIFIED;
        }
    }

    @Transactional
    public HttpStatus registerUser(UserDto u){
        try {
            entityManager.joinTransaction();
            entityManager.createNativeQuery("insert into user(full_name, phone, city, street, postal_code, email,user_role)" +
                    " values(?,?,?,?,?,?,?)")
                    .setParameter(1,u.getFullName())
                    .setParameter(2,u.getPhone())
                    .setParameter(3,u.getCity())
                    .setParameter(4,u.getStreet())
                    .setParameter(5,u.getPostalCode())
                    .setParameter(6,u.getEmail())
                    .setParameter(7,"USER")
                    .executeUpdate();
            return HttpStatus.CREATED;
        }catch (Exception e){
            return HttpStatus.NOT_MODIFIED;
        }
    }

    public User getUserByUsername(String username){
        return (User) entityManager.createNativeQuery("select * from user where username=?",User.class)
                .setParameter(1,username)
                .getSingleResult();
    }
}
