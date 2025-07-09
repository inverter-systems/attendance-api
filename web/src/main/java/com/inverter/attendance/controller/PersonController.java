package com.inverter.attendance.controller;

import com.inverter.attendance.dto.LegalPersonDTO;
import com.inverter.attendance.dto.NaturalPersonDTO;
import com.inverter.attendance.dto.PersonDTO;
import com.inverter.attendance.dto.PersonMapper;
import com.inverter.attendance.service.PersonService;
import com.inverter.auth.util.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService service;
    private final PersonMapper mapper;

    public PersonController(PersonService service, PersonMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/natural")
    public ResponseEntity<Response<NaturalPersonDTO>> create(@Valid @RequestBody NaturalPersonDTO personDto, BindingResult result) {
        Response<NaturalPersonDTO> resp = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> resp.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }

        try {
            var naturalPerson = mapper.toEntity(personDto);
            var createdPerson = service.create(naturalPerson);
            resp.setData(mapper.toDto(createdPerson));

        } catch (Exception e) {
            resp.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resp);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/legal")
    public ResponseEntity<Response<LegalPersonDTO>> create(@Valid @RequestBody LegalPersonDTO personDto, BindingResult result) {
        Response<LegalPersonDTO> resp = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> resp.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }

        try {
            var legalPerson = mapper.toEntity(personDto);
            var createdPerson = service.create(legalPerson);
            resp.setData(mapper.toDto(createdPerson));

        } catch (Exception e) {
            resp.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resp);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/fully-registered")
    public ResponseEntity<Response<PersonDTO>> isFullyRegistered(@RequestParam String username) {
        Response<PersonDTO> resp = new Response<>();
        try {
            var personDto = service.findPersonByUsername(username);
            personDto.ifPresent(resp::setData);
            return ResponseEntity.ok(resp);
        } catch (RuntimeException e) {
            resp.getErrors().add(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }
}

