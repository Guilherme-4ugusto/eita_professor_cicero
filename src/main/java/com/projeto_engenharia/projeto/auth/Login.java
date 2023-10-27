package com.projeto_engenharia.projeto.auth;

import jakarta.validation.constraints.NotNull;

public record Login(
		@NotNull(message = "O campo login não pode ser nulo")
		String login,
		@NotNull(message = "O campo senha não deve ser nulo")
		String password
		
		) {

}
