package com.projeto_engenharia.projeto.course;

import com.projeto_engenharia.projeto.enums.Modality;

public record CourseReponseDTO(
		
		Long id,
		String courseName,
		String description,
		Float duration,
		Modality modality,
		String teachername
		
		) {
	public CourseReponseDTO(Course course) {
		this(course.getId(),course.getCourseName(), course.getDescription(), course.getDuration(), course.getModality(), course.getTeachername());
	}
}
