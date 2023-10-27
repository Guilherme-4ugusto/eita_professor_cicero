package com.projeto_engenharia.projeto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoRequestDTO (
		@NotBlank
		@Pattern(regexp = "\\d{8}")
		String cep,
		@NotBlank
		String logradouro, 
		@NotBlank
		String bairro,
		@NotBlank
		String cidade,
		@NotBlank
		String uf,
		String complemento, 
		String numero,
		@NotBlank
		Long id
		){

}
