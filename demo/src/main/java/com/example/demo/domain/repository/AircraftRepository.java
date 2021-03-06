package com.example.demo.domain.repository;

import com.example.demo.domain.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft,Long> {
    boolean existsByCrewId(Long crewId);
}
