package com.inverter.attendance.service;

import java.util.Optional;

import com.inverter.attendance.dto.PersonDTO;
import com.inverter.attendance.entity.LegalPerson;
import com.inverter.attendance.entity.NaturalPerson;

public interface PersonService {

	Optional<PersonDTO> findPersonByUsername(String username);
	NaturalPerson create(NaturalPerson naturalPerson);
	LegalPerson create(LegalPerson legalPerson);

}
