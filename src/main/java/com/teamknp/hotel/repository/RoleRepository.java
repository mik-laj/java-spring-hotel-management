package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Role;
import com.teamknp.hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

