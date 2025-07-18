package com.metadata.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.metadata.service.model.FileMetadata;
import com.metadata.service.repository.FileMetadataRepository;

@RestController
@RequestMapping("/api/metadata")
@RequiredArgsConstructor
public class MetadataController {

    private final FileMetadataRepository repository;

    @PostMapping
    public ResponseEntity<String> saveMetadata(@RequestBody FileMetadata metadata) {
        repository.save(metadata);
        return ResponseEntity.ok("Metadata saved");
    }
}
