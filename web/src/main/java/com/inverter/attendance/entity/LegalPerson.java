package com.inverter.attendance.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "LEGAL_ENTITIES")
@Data
@EqualsAndHashCode(callSuper=false)
public class LegalPerson extends Person {
	
    private static final long serialVersionUID = -6915459456390878591L;

    @Column(name = "trade_name", length = 150)
    private String tradeName;

    @Column(name = "ein", nullable = false, unique = true, length = 18)
    private String ein;

    @Column(name = "state_registration", length = 20)
    private String stateRegistration;

    @Column(name = "municipal_registration", length = 20)
    private String municipalRegistration;

    @Column(name = "foundation_date")
    private LocalDate foundationDate;

    @Column(name = "company_size", length = 30)
    private String companySize;

    @Column(name = "legal_nature", length = 100)
    private String legalNature;

    @Column(name = "primary_activity", length = 100)
    private String primaryActivity;

    @Column(name = "secondary_activities", length = 500)
    private String secondaryActivities;
}    
