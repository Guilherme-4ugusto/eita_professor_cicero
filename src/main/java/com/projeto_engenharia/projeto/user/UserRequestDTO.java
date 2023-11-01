package com.projeto_engenharia.projeto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO (
		@Email(message = "Formato de e-mail inválido")
		String email,
		@Size(min = 6, max = 20, message = "A senha deve ter no mínimo 6 e no máximo 20 caracteres.")
	    @Pattern(
			//Pattern criado afim de validar a senha
	        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
	        message = "A senha deve conter pelo menos uma letra minúscula, uma letra maiúscula, um número e um caractere especial."
	    )
		String password,
		String isActive
		){

}
