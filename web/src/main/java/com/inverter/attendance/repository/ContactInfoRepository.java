package com.inverter.attendance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inverter.attendance.entity.ContactInfo;
import com.inverter.attendance.entity.Person;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {

	Optional<ContactInfo> findByPerson(Person person);
	
}