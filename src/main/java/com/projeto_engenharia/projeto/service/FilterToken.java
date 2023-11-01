package com.projeto_engenharia.projeto.service;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto_engenharia.projeto.user.User;
import com.projeto_engenharia.projeto.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token;
			
			String authorizationHeader = request.getHeader("Authorization");

			if(authorizationHeader != null) {
				token = authorizationHeader.replace("Bearer ", "");
				String subject = this.tokenService.getSubject(token);

				User user = this.userRepository.findByEmail(subject);

				if(user == null){
					throw new EntityNotFoundException("Usuario para autenticacao nao esta cadastrado");
				}

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
						null, user.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
			filterChain.doFilter(request, response);
		} catch (EntityNotFoundException e) {
			Map<String, String> errors = new HashMap<>();
			errors.put("error", e.getMessage());
			response.setStatus(HttpStatus.NOT_FOUND.value());
			response.getWriter().write(new ObjectMapper().writeValueAsString(errors));
			response.setContentType("application/json; charset=utf-8");
		} catch (TokenExpiredException e){
			Map<String, String> errors = new HashMap<>();
			errors.put("error", e.getMessage());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(new ObjectMapper().writeValueAsString(errors));
			response.setContentType("application/json; charset=utf-8");
		}
		
	}
	
}

