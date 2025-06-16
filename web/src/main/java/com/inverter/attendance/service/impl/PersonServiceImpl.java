package com.inverter.attendance.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inverter.attendance.entity.NaturalPerson;
import com.inverter.attendance.entity.Person;
import com.inverter.attendance.repository.PersonRepository;
import com.inverter.attendance.service.PersonService;
import com.inverter.auth.repository.UserRepository;

@Service
public class PersonServiceImpl implements PersonService {
	
	private PersonRepository repo;
	private UserRepository userRepo;
	
	public PersonServiceImpl(PersonRepository repo, UserRepository userRepo) {
		this.repo = repo;
		this.userRepo = userRepo;
	}

	@Override
	public Optional<Person> findPersonByUsername(String username) {
		var user = userRepo.findByUsername(username);
		
		if (user.isEmpty()) {
			return Optional.empty();
		}
		
		return repo.findByUser(user.get());
	}

	@Override
	public NaturalPerson create(NaturalPerson naturalPerson) {
		return repo.save(naturalPerson);
	}

}
