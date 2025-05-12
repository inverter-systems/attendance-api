package com.inverter.auth.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROLES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
	
	private static final long serialVersionUID = -660280685434830519L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column
	private String description;

	@Column(updatable = false)
    @CreationTimestamp
    @JsonProperty(access = Access.WRITE_ONLY)
    private LocalDateTime createdAt;
	
    @UpdateTimestamp
    @JsonProperty(access = Access.WRITE_ONLY)
    private LocalDateTime updatedAt;
}
