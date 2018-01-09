package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Users;

public interface UserServices {
    Users findByUserName(Long username);
}
