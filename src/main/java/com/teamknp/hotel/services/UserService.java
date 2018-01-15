package com.teamknp.hotel.services;

import com.teamknp.hotel.entity.User;

public interface UserService {
    User findByUsername(String username);
}
