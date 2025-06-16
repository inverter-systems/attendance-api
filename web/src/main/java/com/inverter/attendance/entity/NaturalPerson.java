package com.inverter.attendance.entity;

import java.time.LocalDate;

import com.inverter.attendance.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="NATURAL_PEOPLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class NaturalPerson extends Person {
	
	private static final long serialVersionUID = 2888412794417440622L;

	@Column(name = "ssn", nullable = false, unique = true, length = 20)
    private String ssn;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 20)
    private Gender gender;

    @Column(name = "nationality", length = 50)
    private String nationality;

    @Column(name = "marital_status", length = 20)
    private String maritalStatus;
    
    @Column(name = "professional_occupation", length = 100)
    private String professionalOccupation;
}
