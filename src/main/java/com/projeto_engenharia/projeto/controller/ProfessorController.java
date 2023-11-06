package com.projeto_engenharia.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_engenharia.projeto.professor.Professor;
import com.projeto_engenharia.projeto.professor.ProfessorRepository;
import com.projeto_engenharia.projeto.professor.ProfessorRequestDTO;
import com.projeto_engenharia.projeto.professor.ProfessorResponseDTO;
import com.projeto_engenharia.projeto.user.User;
import com.projeto_engenharia.projeto.user.UserRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("professor")
@Tag(name = "Professores", description = "Rotas para gestão dos professores API")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<ProfessorResponseDTO> getAll() {
    	List<ProfessorResponseDTO> professores = professorRepository.findAll().stream().map(ProfessorResponseDTO::new).toList();
        return professores;	
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{userId}")
    public ResponseEntity<ProfessorResponseDTO> saveProfessor(@PathVariable Long userId, @Valid @RequestBody ProfessorRequestDTO data){
    	User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));
    	Professor professor = professorRepository.save(new Professor(data, user));
        ProfessorResponseDTO response = new ProfessorResponseDTO(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
