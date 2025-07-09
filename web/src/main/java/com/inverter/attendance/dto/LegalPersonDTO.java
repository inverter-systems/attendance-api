package com.inverter.attendance.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class LegalPersonDTO extends PersonDTO {
    private String tradeName;
    private String ein;
    private String stateRegistration;
    private String municipalRegistration;
    private LocalDate foundationDate;
    private String companySize;
    private String legalNature;
    private String primaryActivity;
    private String secondaryActivities;
}