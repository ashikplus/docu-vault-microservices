package com.file.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.file.service.model.FileMetadata;

@FeignClient(name = "metadata-service")
public interface MetadataClient {
    @PostMapping("/api/metadata")
    void saveMetadata(@RequestBody FileMetadata metadata);
}
