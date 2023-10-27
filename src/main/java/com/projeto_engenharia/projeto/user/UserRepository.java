package com.projeto_engenharia.projeto.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
	
	User findByLogin(String email);

}
