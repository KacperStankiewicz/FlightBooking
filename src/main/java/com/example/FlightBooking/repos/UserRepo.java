package com.example.FlightBooking.repos;

import com.example.FlightBooking.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepo {
    private final EntityManager entityManager;

    public User getUserByID(int id){
        return (User) entityManager.createNativeQuery("SELECT * From user where user_id = ?", User.class).setParameter(1,id)
                .getSingleResult();
    }
}
