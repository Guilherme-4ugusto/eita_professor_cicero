package com.projeto_engenharia.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_engenharia.projeto.course.Course;
import com.projeto_engenharia.projeto.course.CourseRepository;
import com.projeto_engenharia.projeto.matricula.Matricula;
import com.projeto_engenharia.projeto.matricula.MatriculaRepository;
import com.projeto_engenharia.projeto.matricula.MatriculaResponseDTO;
import com.projeto_engenharia.projeto.user.User;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("matricula")
public class MatriculaController {
	
	@Autowired
	private MatriculaRepository matriculaRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@PostMapping("/{idCourse}")
	public ResponseEntity<MatriculaResponseDTO> saveMatricula(@PathVariable Long idCourse){
		Course course = courseRepository.findById(idCourse).orElseThrow(() -> new EntityNotFoundException("Curso nao encontrado"));
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Matricula matricula = matriculaRepository.save( new Matricula(user, course));
		MatriculaResponseDTO response = new MatriculaResponseDTO(matricula);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
}
