package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.User;
import com.teamknp.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserServices {
    @Autowired
    protected UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
