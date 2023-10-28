package com.projeto_engenharia.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_engenharia.projeto.enums.Role;
import com.projeto_engenharia.projeto.professor.ProfessorResponseDTO;
import com.projeto_engenharia.projeto.user.User;
import com.projeto_engenharia.projeto.user.UserRepository;
import com.projeto_engenharia.projeto.user.UserRequestDTO;
import com.projeto_engenharia.projeto.user.UserResponseDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/{role}")
	public ResponseEntity saveUser(@PathVariable String role,@Valid @RequestBody UserRequestDTO data) {
		try {
			User userRequest = new User(data);
			userRequest.setRole(Role.valueOf(role));
			User user = userRepository.save(userRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}

	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<UserResponseDTO> getAll() {
    	List<UserResponseDTO> users = userRepository.findAll().stream().map(UserResponseDTO::new).toList();
        return users;	
    }

}
