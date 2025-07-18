package com.file.service.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.service.client.MetadataClient;
import com.file.service.model.FileMetadata;
import com.file.service.service.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

	private final S3Service s3Service;
    private final MetadataClient metadataClient;
    
	@GetMapping("/upload")
    public String upload() {
        return "This is a secured endpoint!";
    }
	
	@PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileUrl = s3Service.uploadFile(file);
        metadataClient.saveMetadata(new FileMetadata(file.getOriginalFilename(), fileUrl));
        return ResponseEntity.ok(fileUrl);
    }
}
