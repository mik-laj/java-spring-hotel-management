package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Reservation;
import com.teamknp.hotel.entity.User;
import com.teamknp.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    protected UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public Page<User> search(String query, Pageable pageable) {
        return userRepository.search(query, pageable);
    }
}
