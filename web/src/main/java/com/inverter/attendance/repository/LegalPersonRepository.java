package com.inverter.attendance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inverter.attendance.entity.LegalPerson;

@Repository
public interface LegalPersonRepository extends JpaRepository<LegalPerson, Long> {

	Optional<LegalPerson> findById(Long id);	
}