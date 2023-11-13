package com.projeto_engenharia.projeto.aula;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.projeto_engenharia.projeto.course.Course;

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

@Entity(name = "aulas")
@Table(name = "aulas", schema = "plataforma_cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aula {
	
	@Id
	@SequenceGenerator(name = "aulas_seq", sequenceName = "aulas_sequence", initialValue = 600000, allocationSize = 20)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "aulas_seq")
	private Long id;
	private String title;
	private String description;
	private Float duration;
	private String videoUrl;
	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Course course;
	
	
	public Aula(AulaRequestDTO dado, Course course) {
		this.title = dado.title();
		this.description = dado.description();
		this.duration = dado.duration();
		this.course = course;
	}
	
}
