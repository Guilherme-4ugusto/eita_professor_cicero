package com.projeto_engenharia.projeto.professor;

public record ProfessorResponseDTO(Long cpf, String nome, String telefone, String email, String isAdmin, Double valor_hora_aulas) {
        
    public ProfessorResponseDTO(Professor professor){
        this(professor.getCpf(), professor.getNome(), professor.getTelefone(), professor.getEmail(), professor.getIsAdmin(), professor.getValor_hora_aulas());
    }

}
