package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresRepository extends JpaRepository<Address, Integer> {
}
