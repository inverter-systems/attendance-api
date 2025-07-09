package com.inverter.attendance.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "CONTACTS_INFO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo implements Serializable {
	
    private static final long serialVersionUID = -464093110838051076L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private Person person; 
    
	@Column(name = "name", length = 150)
    private String contactName;
	
	@Column(name = "conatct_type", length = 150)
    private String contactType;
    
    @Column(name = "phone", length = 20)
    private String phone;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "is_primary")
    private Boolean primary;
    
}