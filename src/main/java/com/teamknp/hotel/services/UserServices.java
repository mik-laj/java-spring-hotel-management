package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.User;

public interface UserServices {
    User findByUsername(String username);
}
