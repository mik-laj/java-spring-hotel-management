package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
