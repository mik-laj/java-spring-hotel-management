package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Users;
import com.teamknp.hotel.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImp implements UserServices {
    @Autowired
    protected UsersRepository usersRepository;

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
