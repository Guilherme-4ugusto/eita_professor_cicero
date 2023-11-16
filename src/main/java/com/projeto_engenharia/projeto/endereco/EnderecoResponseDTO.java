package com.projeto_engenharia.projeto.endereco;

public record EnderecoResponseDTO(
		Long id,
		String cep,
		String logradouro, 
		String bairro,
		String cidade,
		String uf,
		String complemento, 
		String numero
		
		) {
	
		public EnderecoResponseDTO(Endereco endereco) {
			this(endereco.getId(), endereco.getCep(), endereco.getLogradouro(), endereco.getBairro(), endereco.getCidade(), endereco.getUf(), endereco.getComplemento(), endereco.getNumero());
		}
    
}
