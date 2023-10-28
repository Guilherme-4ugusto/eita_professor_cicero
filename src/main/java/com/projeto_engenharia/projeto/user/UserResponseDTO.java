package com.projeto_engenharia.projeto.user;

import com.projeto_engenharia.projeto.enums.Role;

public record UserResponseDTO(
		String email,
		String password,
		Role role
) {

	public UserResponseDTO(User data){
		this(data.getEmail(), data.getPassword(), data.getRole());
	}

}
