package com.inverter.attendance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inverter.attendance.entity.Person;
import com.inverter.auth.entity.User;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findById(Long id);
	Optional<Person> findByUser(User user);
	
}