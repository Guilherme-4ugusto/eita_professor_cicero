package com.projeto_engenharia.projeto.matricula;

import com.projeto_engenharia.projeto.course.Course;
import com.projeto_engenharia.projeto.user.User;

public record MatriculaRequestDTO(
		User user,
		Course course
		) {

}
