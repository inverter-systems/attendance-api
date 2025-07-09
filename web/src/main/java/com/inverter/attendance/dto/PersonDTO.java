package com.inverter.attendance.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.inverter.auth.entity.User;

import lombok.Data;

@Data
@JsonSubTypes({
        @JsonSubTypes.Type(value = NaturalPersonDTO.class, name = "naturalPerson"),
        @JsonSubTypes.Type(value = LegalPersonDTO.class, name = "legalPerson")
})
public abstract class PersonDTO {
    private Long id;
    private String fullName;
    private List<AddressDTO> addresses;
    private List<ContactInfoDTO> contacts;
    private User user;	
}
