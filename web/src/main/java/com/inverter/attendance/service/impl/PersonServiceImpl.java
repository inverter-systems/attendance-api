package com.inverter.attendance.service.impl;

import com.inverter.attendance.dto.PersonDTO;
import com.inverter.attendance.dto.PersonMapper;
import com.inverter.attendance.entity.LegalPerson;
import com.inverter.attendance.entity.NaturalPerson;
import com.inverter.attendance.repository.PersonRepository;
import com.inverter.attendance.service.PersonService;
import com.inverter.auth.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repo;
    private final UserRepository userRepo;
    private final PersonMapper personMapper;

    public PersonServiceImpl(PersonRepository repo, UserRepository userRepo, PersonMapper personMapper) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.personMapper = personMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonDTO> findPersonByUsername(String username) {
        return userRepo.findByUsername(username)
                .flatMap(repo::findByUser)
                .map(personMapper::toDto);
    }

    @Override
    @Transactional
    public NaturalPerson create(NaturalPerson naturalPerson) {
        userRepo.findByEmail(naturalPerson.getUser().getEmail())
                .ifPresent(naturalPerson::setUser);
        return repo.save(naturalPerson);
    }

    @Override
    @Transactional
    public LegalPerson create(LegalPerson legalPerson) {
        userRepo.findByEmail(legalPerson.getUser().getEmail())
                .ifPresent(legalPerson::setUser);
        return repo.save(legalPerson);
    }

}
