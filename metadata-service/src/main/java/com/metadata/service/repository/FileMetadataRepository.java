package com.metadata.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metadata.service.model.FileMetadata;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {
}
