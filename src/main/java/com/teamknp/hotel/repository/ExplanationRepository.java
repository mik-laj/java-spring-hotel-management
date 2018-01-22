package com.teamknp.hotel.repository;

import com.teamknp.hotel.entity.Claim;
import com.teamknp.hotel.entity.Explanation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExplanationRepository extends  JpaRepository<Explanation, Long>{

}
