package com.projeto_engenharia.projeto.user;

import java.util.Date;

import com.projeto_engenharia.projeto.enums.Role;
import com.projeto_engenharia.projeto.enums.UserStatus;

public record UserResponseDTO(
		Long id,
		String email,
		Role role,
		String status,
		Date createDate
) {

	public UserResponseDTO(User data){
		this(data.getId(), data.getEmail(), data.getRole(), UserStatus.fromValue(data.getIsActive()).getDescription(), data.getCreateDate());
	}

}
