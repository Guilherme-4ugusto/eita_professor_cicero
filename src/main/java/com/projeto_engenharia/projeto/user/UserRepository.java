package com.projeto_engenharia.projeto.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
	
	User findByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE users u SET u.isAtivo = :newValue WHERE u.id = :userID")
    void updateActiveUserStatus(@Param("userID") Long userID ,@Param("newValue") Boolean isAtivo);

}
