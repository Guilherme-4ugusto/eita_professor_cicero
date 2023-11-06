package com.projeto_engenharia.projeto.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Student findByUser_Id(Long userId);

}
