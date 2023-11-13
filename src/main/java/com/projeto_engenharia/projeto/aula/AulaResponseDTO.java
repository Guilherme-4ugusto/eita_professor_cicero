package com.projeto_engenharia.projeto.aula;

public record AulaResponseDTO(
		Long id,
		String title,
		String description,
		Float duration
		) {
	
	public AulaResponseDTO(Aula aula) {
		this(aula.getId(), aula.getTitle(), aula.getDescription(), aula.getDuration());
	}

}
