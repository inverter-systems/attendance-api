package com.inverter.attendance.dto;

import com.inverter.attendance.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class NaturalPersonDTO extends PersonDTO {
    private String ssn;
    private LocalDate birthDate;
    private Gender gender;
    private String nationality;
    private String maritalStatus;
    private String professionalOccupation;
}