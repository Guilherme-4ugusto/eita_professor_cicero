package com.projeto_engenharia.projeto.service;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projeto_engenharia.projeto.aula.Aula;
import com.projeto_engenharia.projeto.aula.AulaRepository;


import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
public class AulaService {
	
	@Autowired
	AulaRepository aulaRepository;

    public String saveVideoForaula(Long aulaId, String courseName, MultipartFile video) {
    	
        try {
            String fileName = video.getOriginalFilename();
            String pathDirectory = "src/main/resources/cursos/"+ courseName + "/";
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            File directory = new File(pathDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String randomFileName = UUID.randomUUID().toString() + fileExtension;
            String path = pathDirectory + randomFileName;

            FileUtils.writeByteArrayToFile(new File(path), video.getBytes());
            
            return path; // Retornando o nome do arquivo para salvar no campo de vídeo no objeto do curso
        } catch (IOException e) {
            // Lidar com exceções (por exemplo, lançar uma exceção personalizada, registrar o erro, etc.)
            e.printStackTrace();
            return null; // Ou qualquer outro retorno que indique um erro no salvamento do vídeo
        }
    }
    
    // Método para atualizar o campo videoUrl do curso
    public void updateaulaVideoUrl(Long aulaId, String videoUrl) {
        // Lógica para atualizar o campo de vídeo na entidade do curso com o URL/caminho do vídeo
        Aula aula = aulaRepository.findById(aulaId).orElse(null);
        if (aula != null) {
            aula.setVideoUrl(videoUrl);
            aulaRepository.save(aula);
        }
    }
}
