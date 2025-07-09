package com.inverter.attendance.entity;

import java.io.Serializable;

import com.inverter.attendance.enums.AddressType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ADDRESSES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
	
    private static final long serialVersionUID = 5652944796668528723L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private Person person; 
    
    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    private AddressType type;

	@Column(name = "street", length = 150)
    private String street;
    
    @Column(name = "number", length = 10)
    private String number;
    
    @Column(name = "complement", length = 50)
    private String complement;
    
    @Column(name = "district", length = 50)
    private String district;
    
    @Column(name = "city", length = 50)
    private String city;
    
    @Column(name = "state", length = 2)
    private String state;
    
    @Column(name = "zip_code", length = 8)
    private String zipCode;
    
    @Column(name = "country", length = 30)
    private String country;
    
    @Column(name = "is_primary")
    private boolean primary;
}


