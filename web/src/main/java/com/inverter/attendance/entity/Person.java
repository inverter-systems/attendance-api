package com.inverter.attendance.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inverter.auth.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="PEOPLE")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Person implements Serializable {
	
	private static final long serialVersionUID = -1979218224612144376L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @OneToMany(mappedBy = "person")
    private List<Address> addresses = new ArrayList<>();
    
    @OneToMany(mappedBy = "person")
    private List<ContactInfo> contacts = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
