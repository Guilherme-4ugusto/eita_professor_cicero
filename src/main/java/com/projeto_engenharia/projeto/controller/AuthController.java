package com.projeto_engenharia.projeto.controller;

import java.util.HashMap;
import java.util.Map;

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

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/login")
@Tag(name = "Autorização", description = "Rotas referentes a autorição da API")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<Map<String, String>> login(@RequestBody Login login) {
		Map<String, String> responseData = new HashMap<>();
		try {
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                new UsernamePasswordAuthenticationToken(login.email(), login.password());
	        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	        var usuario = (User) authentication.getPrincipal();
	        String token = tokenService.gerarToken(usuario);
			responseData.put("token", token);
	        return ResponseEntity.ok(responseData);
	    } catch (AuthenticationException ex) {
			responseData.put("error", "Email ou senha incorretos.");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
	    }
		
		
	}
}
