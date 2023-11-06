package com.projeto_engenharia.projeto.student;

import java.time.LocalDate;

import com.projeto_engenharia.projeto.enums.Etnia;
import com.projeto_engenharia.projeto.enums.Genero;
import com.projeto_engenharia.projeto.enums.GrauAcademico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record StudentRequestDTO (

		String cpf,
		@NotBlank(message = "Nome é obrigatório")
		String name,
		@Pattern(regexp = "\\d{11}", message = "Telefone inválido")
		String phoneNumber,
		@Past(message = "A data de nascimento deve estar no passado")
		LocalDate dtBirth,
		Genero gender,
		Etnia ethnicity,
		float perCapitaIncome, 
		GrauAcademico educationLevel, 
		int familySize,
		String disability,
		String financialAssistance,
		String internetAccess,
		String additionalComments
		){

}
