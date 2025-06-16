package com.inverter.attendance.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.inverter.attendance.annotation.Cpf;
import com.inverter.attendance.entity.Address;
import com.inverter.attendance.entity.ContactInfo;
import com.inverter.attendance.entity.NaturalPerson;
import com.inverter.attendance.enums.Gender;
import com.inverter.auth.entity.User;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NaturalPersonDTO {
	
    private Long id;
    
    @NotNull(message = "{person.error.invalid.fullname.null}")
    private String fullName;
    
    @NotNull(message = "{person.error.invalid.address.null}")
    private List<Address> addresses = new ArrayList<>();
    
    @NotNull(message = "{person.error.invalid.contacts.null}")
    private List<ContactInfo> contacts = new ArrayList<>();
    
    @NotNull(message = "{person.error.invalid.user.null}")
    private User user;
    
    @Length(max = 20, message = "{natural.person.error.invalid.ssn.size}")
	@NotNull(message = "{natural.person.error.invalid.ssn.null}")
    @Cpf
    private String ssn;

    @NotNull(message = "{natural.person.error.invalid.birthdate.null}")
    private LocalDate birthDate;

    @NotNull(message = "{natural.person.error.invalid.gender.null}")
    private Gender gender;
    
    @NotNull(message = "{natural.person.error.invalid.nationality.null}")
    private String nationality;

    @NotNull(message = "{natural.person.error.invalid.maritalstatus.null}")
    private String maritalStatus;
    
    @NotNull(message = "{natural.person.error.invalid.professionaloccupation.null}")
    private String professionalOccupation;

	public static NaturalPerson buildNaturalPerson(NaturalPersonDTO personDto) {
		NaturalPerson person = new NaturalPerson();
		person.setFullName(personDto.getFullName());
		person.setAddresses(personDto.getAddresses());
		person.setContacts(personDto.getContacts());
		person.setUser(personDto.getUser());
		
		person.setBirthDate(personDto.getBirthDate());
		person.setGender(personDto.getGender());
		person.setNationality(personDto.getNationality());
		person.setMaritalStatus(personDto.getMaritalStatus());
		person.setProfessionalOccupation(personDto.getProfessionalOccupation());
		person.setSsn(personDto.getSsn());
		
		return person;
	}

	public static NaturalPersonDTO buildNaturalPersonDTO(NaturalPerson naturalPerson) {
		NaturalPersonDTO person = new NaturalPersonDTO();
		person.setFullName(naturalPerson.getFullName());
		person.setAddresses(naturalPerson.getAddresses());
		person.setContacts(naturalPerson.getContacts());
		person.setUser(naturalPerson.getUser());
		
		person.setBirthDate(naturalPerson.getBirthDate());
		person.setGender(naturalPerson.getGender());
		person.setNationality(naturalPerson.getNationality());
		person.setMaritalStatus(naturalPerson.getMaritalStatus());
		person.setProfessionalOccupation(naturalPerson.getProfessionalOccupation());
		person.setSsn(naturalPerson.getSsn());
		
		return person;
	}

}
