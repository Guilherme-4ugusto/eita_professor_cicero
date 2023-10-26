package com.projeto_engenharia.projeto.professor;

public record ProfessorRequestDTO(Long cpf, String nome, String telefone, String email, String isAdmin, Double valor_hora_aulas) {
    
}