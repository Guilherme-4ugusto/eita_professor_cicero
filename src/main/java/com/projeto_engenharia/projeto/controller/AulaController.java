package com.projeto_engenharia.projeto.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projeto_engenharia.projeto.aula.Aula;
import com.projeto_engenharia.projeto.aula.AulaRepository;
import com.projeto_engenharia.projeto.aula.AulaRequestDTO;
import com.projeto_engenharia.projeto.aula.AulaResponseDTO;
import com.projeto_engenharia.projeto.course.Course;
import com.projeto_engenharia.projeto.course.CourseRepository;
import com.projeto_engenharia.projeto.service.AulaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aulas")
public class AulaController {
	
	@Autowired
	AulaService aulaService;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private AulaRepository aulaRepository;
	

	
	@PostMapping("/{idCourse}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_PROFESSOR')")
	public ResponseEntity<AulaResponseDTO> saveAula(@Valid @RequestBody AulaRequestDTO dado, @PathVariable Long idCourse){
		Course course = courseRepository.findById(idCourse).orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
		Aula aula = aulaRepository.save(new Aula(dado, course));
		AulaResponseDTO response = new AulaResponseDTO(aula);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping("/{idAula}/video")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_PROFESSOR')")
	public ResponseEntity<String> saveVideo(@RequestParam("video") MultipartFile video, @PathVariable Long idAula){
		Aula aula = aulaRepository.findById(idAula).orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
		String videoUrl = aulaService.saveVideoForaula(idAula, aula.getCourse().getCourseName(), video);
		aulaService.updateaulaVideoUrl(idAula, videoUrl);
		return ResponseEntity.ok("Vídeo enviado com sucesso para o aula!");
	}
	
	@GetMapping("{idAula}")
	public ResponseEntity<Resource> getAulaVideo(@PathVariable Long idAula){
		Aula aula = aulaRepository.findById(idAula).orElseThrow(() -> new EntityNotFoundException("Aula não encontrado"));
		try {
            // Lógica para ler o arquivo do sistema de arquivos
            File file = new File(aula.getVideoUrl());
            Path path = file.toPath();
            Resource resource = new UrlResource(path.toUri());
            String fileExtension = file.toString().substring(file.toString().lastIndexOf(".")).replace(".", "");
            System.out.println(fileExtension);
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("video/" + fileExtension))
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}

}
