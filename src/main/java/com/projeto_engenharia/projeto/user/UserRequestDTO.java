package com.projeto_engenharia.projeto.user;

import com.projeto_engenharia.projeto.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO (
		@Email(message = "Formato de e-mail inválido")
		String login,
		@Size(min = 6, max = 20, message = "A senha deve ter no mínimo 6 e no máximo 20 caracteres.")
	    @Pattern(
	        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
	        message = "A senha deve conter pelo menos uma letra minúscula, uma letra maiúscula, um número e um caractere especial."
	    )
		String password,
		@NotNull
		Role role
		){

}
