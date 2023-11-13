package com.projeto_engenharia.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_engenharia.projeto.course.Course;
import com.projeto_engenharia.projeto.course.CourseReponseDTO;
import com.projeto_engenharia.projeto.course.CourseRepository;
import com.projeto_engenharia.projeto.course.CourseRequestDTO;
import com.projeto_engenharia.projeto.user.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_PROFESSOR')")
	public ResponseEntity<CourseReponseDTO> saveCourse(@Valid @RequestBody CourseRequestDTO dado){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Course course = courseRepository.save(new Course(dado, user));
		CourseReponseDTO response = new CourseReponseDTO(course);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}	

}
