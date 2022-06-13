package com.example.demo.domain.repository;

import com.example.demo.domain.entity.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember,Long> {
}
