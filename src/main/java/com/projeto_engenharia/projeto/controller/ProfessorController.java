package com.projeto_engenharia.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_engenharia.projeto.professor.Professor;
import com.projeto_engenharia.projeto.professor.ProfessorRepository;
import com.projeto_engenharia.projeto.professor.ProfessorRequestDTO;
import com.projeto_engenharia.projeto.professor.ProfessorResponseDTO;


@RestController
@RequestMapping("professor")
public class ProfessorController {
    @Autowired
    private ProfessorRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ProfessorResponseDTO> getAll() {
        List<ProfessorResponseDTO> professores = repository.findAll().stream().map(ProfessorResponseDTO::new).toList();
        return professores;
    }
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveProfessor(@RequestBody ProfessorRequestDTO data){
        Professor professor = new Professor(data);
        repository.save(professor);
    }
}
