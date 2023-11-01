package com.projeto_engenharia.projeto.professor;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    
}

