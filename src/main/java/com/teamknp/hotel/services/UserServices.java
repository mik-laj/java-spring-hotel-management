package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.Users;

public interface UserServices {
    Users findByUsername(String username);
}
