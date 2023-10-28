package com.projeto_engenharia.projeto.professor;

import java.time.LocalDate;

import com.projeto_engenharia.projeto.enums.Especialidade;
import com.projeto_engenharia.projeto.enums.Etnia;
import com.projeto_engenharia.projeto.enums.Genero;
import com.projeto_engenharia.projeto.enums.GrauAcademico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record ProfessorRequestDTO(
		Long cpf,
		@NotBlank(message = "Nome é obrigatório")
		String nome,
		@Pattern(regexp = "\\d{11}", message = "Telefone inválido")
		String telefone,
		@Past(message = "A data de nascimento deve estar no passado")
		LocalDate dtNascimento,
		@NotNull(message = "Grau acadêmico é obrigatório")
		GrauAcademico grauAcademico,
		@NotNull(message = "Especialidade é obrigatória")
		Especialidade especialidade,
		@NotBlank(message = "Instituição formadora é obrigatória")
		String instituicaoFormadora,
		@Positive(message = "O valor das horas de aulas deve ser positivo")
		Double valorHoraAulas,
		@NotNull(message = "Gênero é obrigatório")
		Genero genero,
		@NotNull(message = "Etnia é obrigatória")
		Etnia etnia
		) {
    
}