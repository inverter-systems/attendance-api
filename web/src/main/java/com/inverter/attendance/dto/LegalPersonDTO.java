package com.inverter.attendance.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.inverter.attendance.entity.Address;
import com.inverter.attendance.entity.ContactInfo;
import com.inverter.auth.entity.User;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LegalPersonDTO {
	
    private Long id;
    
    @NotNull(message = "{person.error.invalid.fullname.null}")
    private String fullName;
    
    @NotNull(message = "{person.error.invalid.address.null}")
    private List<Address> addresses = new ArrayList<>();
    
    @NotNull(message = "{person.error.invalid.contacts.null}")
    private List<ContactInfo> contacts = new ArrayList<>();
    
    @NotNull(message = "{person.error.invalid.user.null}")
    private User user;
    
    @NotNull(message = "{legal.person.error.invalid.tradename.null}")
    @Length(max = 150, message = "{legal.person.error.invalid.tradename.size}")
    private String tradeName;

    @NotNull(message = "{legal.person.error.invalid.ein.null}")
    @Length(max = 18, message = "{legal.person.error.invalid.ein.size}")
    private String ein;

    @NotNull(message = "{legal.person.error.invalid.stateregistration.null}")
    @Length(max = 20, message = "{legal.person.error.invalid.stateregistration.size}")
    private String stateRegistration;

    @NotNull(message = "{legal.person.error.invalid.municipalregistration.null}")
    @Length(max = 20, message = "{legal.person.error.invalid.municipalregistration.size}")
    private String municipalRegistration;

    private LocalDate foundationDate;

    @Length(max = 30, message = "{legal.person.error.invalid.companysize.size}")
    private String companySize;

    @Length(max = 100, message = "{legal.person.error.invalid.legalnature.size}")
    private String legalNature;

    @Length(max = 100, message = "{legal.person.error.invalid.primaryactivity.size}")
    private String primaryActivity;

    @Length(max = 500, message = "{legal.person.error.invalid.secondaryactivities.size}")
    private String secondaryActivities;

}
