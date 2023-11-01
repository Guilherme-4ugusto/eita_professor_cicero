package com.projeto_engenharia.projeto.user;

import com.projeto_engenharia.projeto.enums.Role;
import com.projeto_engenharia.projeto.enums.UserStatus;

public record UserResponseDTO(
		String email,
		String password,
		Role role,
		String status
) {

	public UserResponseDTO(User data){
		this(data.getEmail(), data.getPassword(), data.getRole(), UserStatus.fromValue(data.getIsActive()).getDescription());
	}

}
