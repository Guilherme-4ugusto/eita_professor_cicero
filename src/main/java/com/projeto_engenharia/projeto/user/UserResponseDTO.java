package com.projeto_engenharia.projeto.user;

import com.projeto_engenharia.projeto.enums.Role;

public record UserResponseDTO(
		String email,
		String password,
		Role role
		) {

}
