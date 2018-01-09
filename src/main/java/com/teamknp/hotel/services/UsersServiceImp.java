package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Users;
import com.teamknp.hotel.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImp implements UserServices {
    @Override
    public Users findByUserName(Long username) {
        return null;
    }
    @Autowired
    protected UsersRepository usersRepository;


}
