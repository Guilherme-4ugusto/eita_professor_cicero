package com.projeto_engenharia.projeto.course;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.projeto_engenharia.projeto.enums.Modality;
import com.projeto_engenharia.projeto.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity(name = "courses")
@Table(name = "courses", schema = "plataforma_cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
	
	@Id
	@SequenceGenerator(name = "course_seq", sequenceName = "course_sequence", initialValue = 300000, allocationSize = 20)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "course_seq")
	private Long id;
	private String courseName;
	private String description;
	private Float duration;
	@Enumerated(EnumType.STRING)
	private Modality modality;
	private String teachername;
	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
	
	public Course(CourseRequestDTO dado, User user) {
		this.courseName = dado.courseName();
		this.description = dado.description();
		this.duration = dado.duration();
		this.modality = dado.modality();
		this.teachername = dado.teachername();
		this.user = user;
	}

}
