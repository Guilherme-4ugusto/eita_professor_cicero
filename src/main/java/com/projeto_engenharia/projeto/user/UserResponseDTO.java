package com.projeto_engenharia.projeto.user;

import com.projeto_engenharia.projeto.enums.Role;
import com.projeto_engenharia.projeto.enums.UsuarioStatus;

public record UserResponseDTO(
		String email,
		String password,
		Role role,
		String status
) {

	public UserResponseDTO(User data){
		this(data.getEmail(), data.getPassword(), data.getRole(), UsuarioStatus.fromValue(data.getIsAtivo()).getDescription());
	}

}
