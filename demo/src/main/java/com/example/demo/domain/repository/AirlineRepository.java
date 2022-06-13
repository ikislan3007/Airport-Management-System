package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline,Long> {
    Airline findByAircraftsId(Long Id);
}
