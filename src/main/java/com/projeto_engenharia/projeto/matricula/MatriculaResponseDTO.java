package com.projeto_engenharia.projeto.matricula;

import com.projeto_engenharia.projeto.course.Course;
import com.projeto_engenharia.projeto.user.User;

public record MatriculaResponseDTO(
		Long id,
		User user,
		Course course
		
		) {
	public MatriculaResponseDTO(Matricula matricula){
		this(matricula.getId(),matricula.getUser(), matricula.getCourse() );
	}

}
