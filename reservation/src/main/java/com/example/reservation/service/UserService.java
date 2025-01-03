package com.example.reservation.service;



import com.example.reservation.model.User;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsername(String username);
    User saveUser(User user);
}
