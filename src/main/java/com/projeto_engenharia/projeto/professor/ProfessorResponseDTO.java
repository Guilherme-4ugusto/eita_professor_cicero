package com.projeto_engenharia.projeto.professor;

import java.time.LocalDate;

import com.projeto_engenharia.projeto.enums.Especialidade;
import com.projeto_engenharia.projeto.enums.Etnia;
import com.projeto_engenharia.projeto.enums.Genero;
import com.projeto_engenharia.projeto.enums.GrauAcademico;

public record ProfessorResponseDTO(		
		Long cpf, 
		String nome, 
		String telefone, 
		LocalDate dtNascimento,
		GrauAcademico grauAcademico,
		Especialidade especialidade,
		String instituicaoFormadora,
		Double valorHoraAulas,
		Genero genero,
		Etnia etnia) {
        
    public ProfessorResponseDTO(Professor professor){
        this(professor.getCpf(), professor.getNome(), professor.getTelefone(), professor.getDtNascimento(), professor.getGrauAcademico(), professor.getEspecialidade() , professor.getInstituicaoFormadora() , professor.getValorHoraAulas(), professor.getGenero(), professor.getEtnia());
    }

}
