package com.projeto_engenharia.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@PostMapping("/{role}")
	public ResponseEntity<UserResponseDTO> saveUser(@PathVariable String role,@Valid @RequestBody UserRequestDTO data) {
		User userRequest = new User(data);
		userRequest.setRole(Role.valueOf(role));
		userRequest.setIsActive(UserStatus.fromDescription(data.isActive()).getValue());
		User user = userRepository.save(userRequest);
		UserResponseDTO response = new UserResponseDTO(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{userId}/{userStatus}")
	public ResponseEntity<Map<String,String>> alterActiveUserStatus(@PathVariable Long userId, @PathVariable String userStatus){
		Map<String, String> responseData = new HashMap<>();
    	Boolean userExists = userRepository.existsById(userId);
		if(!userExists){
			responseData.put("error", "O usuario nao esta cadastrado no sistema");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
		}
		userRepository.updateActiveUserStatus(userId, UserStatus.fromDescription(userStatus).getValue());
		responseData.put("message", UserStatus.fromDescription(userStatus).getValue() ? "O usuario foi ativado." : "O usuario foi inativado.");
		return ResponseEntity.status(HttpStatus.OK).body(responseData);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserResponseDTO> alterUserData(@PathVariable Long userId, @RequestBody UserRequestDTO data){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));
		user.setEmail(data.email());
		user.setPassword(passwordEncoder.encode(data.password()));
		user.setIsActive(UserStatus.fromDescription(data.isActive()).getValue());
		userRepository.save(user);
		UserResponseDTO response = new UserResponseDTO(user);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
		List<UserResponseDTO> users = userRepository.findAll().stream().map(UserResponseDTO::new).toList();
		return ResponseEntity.ok(users);	
    }

}
