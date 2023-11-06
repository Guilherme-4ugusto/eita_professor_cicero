package com.projeto_engenharia.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projeto_engenharia.projeto.student.Student;
import com.projeto_engenharia.projeto.student.StudentRepository;
import com.projeto_engenharia.projeto.student.StudentRequestDTO;
import com.projeto_engenharia.projeto.student.StudentResponseDTO;
import com.projeto_engenharia.projeto.user.User;
import com.projeto_engenharia.projeto.user.UserRepository;
import com.projeto_engenharia.projeto.utils.Utils;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("student")
@Tag(name = "Students", description = "Rotas para gestão dos estudantes API")
public class StudentController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping
	public List<StudentResponseDTO> getAll() {
		List<StudentResponseDTO> students = studentRepository.findAll().stream().map(StudentResponseDTO::new).toList();
		return students;
	}

	@PostMapping("/{userId}")
	public ResponseEntity<StudentResponseDTO> saveStudent(@PathVariable Long userId,
			@Valid @RequestBody StudentRequestDTO data) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));
		Student student = studentRepository.save(new Student(data, user));
		StudentResponseDTO response = new StudentResponseDTO(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/update")
	public ResponseEntity<Student> updateStudent(@RequestBody StudentResponseDTO data) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Student student = studentRepository.findByUser_Id(user.getId());		
		Utils.copyNonNullProperties(data, student);
		student = studentRepository.save(student);
		return ResponseEntity.ok().body(student);

	}
}
