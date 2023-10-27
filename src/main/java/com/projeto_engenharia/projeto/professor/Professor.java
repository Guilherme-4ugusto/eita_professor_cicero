package com.projeto_engenharia.projeto.professor;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.projeto_engenharia.projeto.enums.Especialidade;
import com.projeto_engenharia.projeto.enums.Etnia;
import com.projeto_engenharia.projeto.enums.Genero;
import com.projeto_engenharia.projeto.enums.GrauAcademico;
import com.projeto_engenharia.projeto.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Data;

@Table(name = "professores")
@Entity(name = "professores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class Professor {

	@Id
    private Long cpf;
    private String nome;
    private String telefone;
    private LocalDate dtNascimento;
    @Enumerated(EnumType.STRING)
    private GrauAcademico grauAcademico;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    private String instituicaoFormadora;
    private Double valor_hora_aulas;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Enumerated(EnumType.STRING)
    private Etnia etnia;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    
    public Professor(ProfessorRequestDTO data, User user) {
    	this.cpf = data.cpf();
    	this.nome = data.nome();
    	this.telefone = data.telefone();
    	this.dtNascimento = data.dtNascimento();
    	this.grauAcademico = data.grauAcademico();
    	this.especialidade = data.especialidade();
    	this.instituicaoFormadora = data.instituicaoFormadora();
    	this.valor_hora_aulas = data.valor_hora_aulas();
    	this.genero = data.genero();
    	this.etnia = data.etnia();
    	this.user = user;
	}


}
