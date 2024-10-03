package com.marquitos.pizzeria.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserRoleId implements Serializable { 

	private String username; 
	private String role;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserRoleId that)) return false;
		return Objects.equals(username, that.username) && Objects.equals(role,that.role);
	}
}
