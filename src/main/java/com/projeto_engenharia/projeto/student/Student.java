package com.projeto_engenharia.projeto.student;

import java.time.LocalDate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.projeto_engenharia.projeto.enums.Especialidade;
import com.projeto_engenharia.projeto.enums.Etnia;
import com.projeto_engenharia.projeto.enums.Genero;
import com.projeto_engenharia.projeto.enums.GrauAcademico;
import com.projeto_engenharia.projeto.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "students", schema = "plataforma_cursos")
@Entity(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class Student {
	
	@Id
	private String cpf;
	private String name;
	private String phoneNumber;
	private LocalDate dtBirth;
	@Enumerated(EnumType.STRING)
	private Genero gender;
	@Enumerated(EnumType.STRING)
	private Etnia ethnicity;
	private float perCapitaIncome;
	@Enumerated(EnumType.STRING)
	private GrauAcademico educationLevel;
	private int familySize;
	private String disability;
	private String financialAssistance;
	private String internetAccess;
	private String additionalComments;
	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

	public Student(StudentRequestDTO data, User user) {
		this.cpf = data.cpf();
		this.name = data.name();
		this.dtBirth = data.dtBirth();
		this.phoneNumber = data.phoneNumber();
		this.gender = data.gender();
		this.ethnicity = data.ethnicity();
		this.perCapitaIncome = data.perCapitaIncome();
		this.educationLevel = data.educationLevel();
		this.familySize = data.familySize();
		this.disability = data.disability();
		this.financialAssistance = data.financialAssistance();
		this.internetAccess = data.internetAccess();
		this.additionalComments = data.additionalComments();
		this.user = user;
	}
	
	
}
