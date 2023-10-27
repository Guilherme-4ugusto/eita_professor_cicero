package com.projeto_engenharia.projeto.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projeto_engenharia.projeto.user.User;

@Service
public class TokenService {
	
	public String gerarToken(User user) {
		String token = JWT.create()
		        .withIssuer("Projeto")
		        .withSubject(user.getUsername())
		        .withClaim("id", user.getId())
		        .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(10)
                        .toInstant(ZoneOffset.of("-03:00")))
		        .sign(Algorithm.HMAC256("secreta"));
		    return token;
	

	}

	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256("secreta"))
				.withIssuer("Projeto")
				.build().verify(token).getSubject();
	}
}