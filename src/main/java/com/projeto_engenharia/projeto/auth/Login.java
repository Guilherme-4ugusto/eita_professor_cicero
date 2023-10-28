package com.projeto_engenharia.projeto.auth;

import jakarta.validation.constraints.NotNull;

public record Login(
		@NotNull(message = "O campo email não pode ser nulo")
		String email,
		@NotNull(message = "O campo senha não deve ser nulo")
		String password
		
		) {

}
