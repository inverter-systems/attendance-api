package com.inverter.attendance.service;

import java.util.Optional;

import com.inverter.attendance.entity.NaturalPerson;
import com.inverter.attendance.entity.Person;

public interface PersonService {

	Optional<Person> findPersonByUsername(String username);
	NaturalPerson create(NaturalPerson naturalPerson);

}
