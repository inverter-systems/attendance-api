package com.inverter.auth.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inverter.auth.entity.Role;
import com.inverter.auth.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long id;

	@Length(min = 3, max = 50, message = "{user.auth.user.error.invalid.username.size}")
	@NotNull(message = "{user.auth.user.error.invalid.username.null}")
	private String username;
	
	@Email(message = "{user.auth.user.error.invalid.email}")
	@NotNull(message = "{user.auth.user.error.invalid.email.null}")
	private String email;
	
	@Length(min = 6, message = "{user.auth.user.error.invalid.password.size}")
	@NotNull(message = "{user.auth.user.error.invalid.password.null}")
	private String password;
	
	private List<Role> roles; 
	private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	
	public User buildUser() {
		return User.builder()
				.id(this.id)
				.email(this.email)
				.password(this.password)
				.username(this.username)
				.build();
	}
	
	public static UserDTO buildUserDTO(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.email(user.getEmail())
				.username(user.getUsername())
				.roles(user.getRoles())
				.active(user.getActive())
				.createdAt(user.getCreatedAt())
				.updatedAt(user.getUpdatedAt())
				.build();
	}
}
