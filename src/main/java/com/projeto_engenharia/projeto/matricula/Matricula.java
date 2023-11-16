package com.projeto_engenharia.projeto.matricula;

import com.projeto_engenharia.projeto.course.Course;
import com.projeto_engenharia.projeto.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Table(name = "matriculas", schema = "plataforma_cursos" )
@Entity(name = "matriculas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Matricula {
	
	@Id
	@SequenceGenerator(name = "matricula_seq", sequenceName = "matricula_sequence", initialValue = 300000, allocationSize = 20)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "matricula_seq")
	private Long id;
    @ManyToOne
	private User user;
    @ManyToOne
	private Course course;
	
	public Matricula(User user, Course course) {
		this.user = user;
		this.course = course;
	}

}
