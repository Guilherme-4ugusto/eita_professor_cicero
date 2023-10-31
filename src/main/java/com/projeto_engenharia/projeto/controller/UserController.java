package com.projeto_engenharia.projeto.controller;

import java.util.List;

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
import com.projeto_engenharia.projeto.enums.UsuarioStatus;
import com.projeto_engenharia.projeto.user.User;
import com.projeto_engenharia.projeto.user.UserRepository;
import com.projeto_engenharia.projeto.user.UserRequestDTO;
import com.projeto_engenharia.projeto.user.UserResponseDTO;

import jakarta.validation.Valid;

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
	@PutMapping("/{userId}/{userStatus}")
	public ResponseEntity alterActiveUserStatus(@PathVariable Long userId, @PathVariable String userStatus){
    	Boolean userExists = userRepository.existsById(userId);
		if(!userExists){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O usuario não está cadastrado no sistema");
		}
		userRepository.updateActiveUserStatus(userId, UsuarioStatus.fromDescription(userStatus).getValue());
		return ResponseEntity.status(HttpStatus.OK).body(UsuarioStatus.fromDescription(userStatus).getValue() ? "O usuario foi ativado." : "O usuario foi inativado.");
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<UserResponseDTO> getAll() {
    	List<UserResponseDTO> users = userRepository.findAll().stream().map(UserResponseDTO::new).toList();
        return users;	
    }

}
