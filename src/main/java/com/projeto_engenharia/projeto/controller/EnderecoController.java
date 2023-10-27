package com.projeto_engenharia.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity saveEndereco(@Valid @RequestBody EnderecoRequestDTO data) {
		User newUser = userRepository.findById(data.id()).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));;
		Endereco newEndereco = enderecoRepository.save(new Endereco(data, newUser));
		return ResponseEntity.status(HttpStatus.CREATED).body("Endereco adicionado com sucesso");
		
	}

}
