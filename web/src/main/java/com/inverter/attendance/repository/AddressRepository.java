package com.inverter.attendance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inverter.attendance.entity.Address;
import com.inverter.attendance.entity.Person;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Optional<Address> findByPerson(Person person);
	
}