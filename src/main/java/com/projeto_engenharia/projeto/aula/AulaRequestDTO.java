package com.projeto_engenharia.projeto.aula;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AulaRequestDTO(
		@NotBlank(message = "O título da aula é obrigatório")
		String title,
		@NotBlank(message = "O título da aula é obrigatório")
		String description,
		@NotNull(message = "A duração da aula não deve ser nula")
		Float duration
		){

}
