package com.projeto_engenharia.projeto.student;

import java.time.LocalDate;

import com.projeto_engenharia.projeto.enums.Etnia;
import com.projeto_engenharia.projeto.enums.Genero;
import com.projeto_engenharia.projeto.enums.GrauAcademico;

public record StudentResponseDTO (
		String cpf,
		String name,
		String phoneNumber,
		LocalDate dtBirth,
		Genero gender,
		Etnia ethnicity,
		Float perCapitaIncome, 
		GrauAcademico educationLevel, 
		Integer familySize,
		String disability,
		String financialAssistance,
		String internetAccess,
		String additionalComments
		){
	
    public StudentResponseDTO(Student student){
        this(student.getCpf(), student.getName(), student.getPhoneNumber(), student.getDtBirth(), student.getGender(), student.getEthnicity(), student.getPerCapitaIncome(), student.getEducationLevel(), student.getFamilySize(), student.getDisability(), student.getFinancialAssistance(), student.getInternetAccess(), student.getAdditionalComments());
    }

}
