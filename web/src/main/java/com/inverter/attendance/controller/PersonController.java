package com.inverter.attendance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inverter.attendance.dto.NaturalPersonDTO;
import com.inverter.attendance.entity.Person;
import com.inverter.attendance.service.PersonService;
import com.inverter.auth.util.Response;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	private PersonService service;
	
	public PersonController(PersonService service) {
		this.service = service;
	}
	
	@PostMapping("/natural")
	public ResponseEntity<Response<NaturalPersonDTO>> create(@Valid @RequestBody NaturalPersonDTO personDto, BindingResult result) {
		Response<NaturalPersonDTO> resp = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> resp.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
		
		try {
			var naturalPerson = NaturalPersonDTO.buildNaturalPerson(personDto);
			naturalPerson = service.create(naturalPerson);

			resp.setData(NaturalPersonDTO.buildNaturalPersonDTO(naturalPerson));
			
		} catch (Exception e) {
			resp.getErrors().add(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(resp);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	
	@GetMapping("/fully-registered")
    public ResponseEntity<Response<Person>> isFullyRegistered(@RequestParam String username) {
		Response<Person> resp = new Response<>();
        try {
        	var person = service.findPersonByUsername(username);
        	if (person.isPresent()) {
        		resp.setData(person.get());
        		return ResponseEntity.ok(resp);
        	}
        	resp.setData(new Person());
        	return ResponseEntity.ok(resp);            
        } catch (RuntimeException e) {
        	resp.getErrors().add(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }
}
