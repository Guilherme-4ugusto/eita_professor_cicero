package com.projeto_engenharia.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_engenharia.projeto.endereco.Endereco;
import com.projeto_engenharia.projeto.endereco.EnderecoRepository;
import com.projeto_engenharia.projeto.endereco.EnderecoRequestDTO;
import com.projeto_engenharia.projeto.user.UserRepository;
import com.projeto_engenharia.projeto.user.User;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("endereco")
public class EnderecoController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
    @PostMapping("/{userId}")
    public ResponseEntity<Endereco> saveEndereco(@PathVariable Long userId, @Valid  @RequestBody EnderecoRequestDTO data) {
    	User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));
		Endereco endereco = enderecoRepository.save(new Endereco(data, user));
		return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
	}

}
