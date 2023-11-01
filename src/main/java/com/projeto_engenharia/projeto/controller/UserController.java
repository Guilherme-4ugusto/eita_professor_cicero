package com.projeto_engenharia.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_engenharia.projeto.enums.Role;
import com.projeto_engenharia.projeto.enums.UserStatus;
import com.projeto_engenharia.projeto.user.User;
import com.projeto_engenharia.projeto.user.UserRepository;
import com.projeto_engenharia.projeto.user.UserRequestDTO;
import com.projeto_engenharia.projeto.user.UserResponseDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/{role}")
	public ResponseEntity saveUser(@PathVariable String role,@Valid @RequestBody UserRequestDTO data) {
		User userRequest = new User(data);
		userRequest.setRole(Role.valueOf(role));
		userRequest.setIsActive(UserStatus.fromDescription(data.isActive()).getValue());
		User user = userRepository.save(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/{userId}/{userStatus}")
	public ResponseEntity alterActiveUserStatus(@PathVariable Long userId, @PathVariable String userStatus){
    	Boolean userExists = userRepository.existsById(userId);
		if(!userExists){
			Map<String, String> errors = new HashMap<>();
			errors.put("error", "O usuario não está cadastrado no sistema");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
		}
		userRepository.updateActiveUserStatus(userId, UserStatus.fromDescription(userStatus).getValue());
		return ResponseEntity.status(HttpStatus.OK).body(UserStatus.fromDescription(userStatus).getValue() ? "O usuario foi ativado." : "O usuario foi inativado.");
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/{userId}")
	public ResponseEntity alterUserData(@PathVariable Long userId, @RequestBody UserRequestDTO data){
    	User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
		user.setEmail(data.email());
		user.setPassword(data.password());
		user.setIsActive(UserStatus.fromDescription(data.isActive()).getValue());
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity getAll() {
		List<UserResponseDTO> users = userRepository.findAll().stream().map(UserResponseDTO::new).toList();
		return ResponseEntity.ok(users);	
    }

}
