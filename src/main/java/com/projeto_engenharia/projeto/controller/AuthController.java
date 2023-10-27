package com.projeto_engenharia.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_engenharia.projeto.auth.Login;
import com.projeto_engenharia.projeto.service.TokenService;
import com.projeto_engenharia.projeto.user.User;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/login")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity login(@RequestBody Login login) {
		try {
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                new UsernamePasswordAuthenticationToken(login.login(), login.password());
	        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	        var usuario = (User) authentication.getPrincipal();
	        String token = tokenService.gerarToken(usuario);
	        return ResponseEntity.ok(token);
	    } catch (AuthenticationException ex) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos");
	    }
		
		
	}
}
