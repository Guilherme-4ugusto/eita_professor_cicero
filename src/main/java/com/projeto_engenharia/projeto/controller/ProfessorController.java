package com.projeto_engenharia.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;
    
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ProfessorResponseDTO> getAll() {
    	List<ProfessorResponseDTO> professores = professorRepository.findAll().stream().map(ProfessorResponseDTO::new).toList();
        return professores;	
    }
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/{userId}")
    public ResponseEntity saveProfessor(@PathVariable Long userId, @Valid @RequestBody ProfessorRequestDTO data){
    	User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    	Professor professor = professorRepository.save(new Professor(data, user));
        return ResponseEntity.status(HttpStatus.CREATED).body(professor.getUser().getId());
    }
}
