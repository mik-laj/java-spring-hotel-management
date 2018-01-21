package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.KeyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyStatusRepository extends JpaRepository<KeyStatus, Long> {
}
