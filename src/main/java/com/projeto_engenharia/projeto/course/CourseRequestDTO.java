package com.projeto_engenharia.projeto.course;

import com.projeto_engenharia.projeto.enums.Modality;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequestDTO(
		@NotBlank(message = "Nome do curso é obrigatório")
		String courseName,
		@NotBlank(message = "Descrição do curso é obrigatório")
		String description,
		@NotNull
		Float duration,
		@NotNull(message = "Modalidade é obrigatório")
		Modality modality,
		@NotBlank(message = "Nome do professor é obrigatório")
		String teachername
		) {

}
