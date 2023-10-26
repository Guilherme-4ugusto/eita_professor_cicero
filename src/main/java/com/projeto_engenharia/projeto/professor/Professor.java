package com.projeto_engenharia.projeto.professor;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Table(name = "professores")
@Entity(name = "professores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class Professor {
    @Id
    private Long cpf;
    private String nome;
    private String telefone;
    private String email;
    private String isAdmin;
    private Double valor_hora_aulas;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dt_criacao;

    public Professor(ProfessorRequestDTO data){
        this.cpf = data.cpf();
        this.nome = data.nome();
        this.telefone = data.telefone();
        this.email = data.email();
        this.isAdmin = data.isAdmin();
        this.valor_hora_aulas = data.valor_hora_aulas();
    }
}
