package com.xplug.tech.filestorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class StorageService {

    private final Logger log = LoggerFactory.getLogger(StorageService.class);

    private final FileSystemStorageService fileSystemStorageService;

    public StorageService(FileSystemStorageService fileSystemStorageService) {
        this.fileSystemStorageService = fileSystemStorageService;
    }

    public FileDTO uploadFile(MultipartFile file) throws Exception {
        Path uploadedFile = this.fileSystemStorageService.store(file);

        String originalFilename = file.getOriginalFilename();

        log.info("uploadedFile: {}", uploadedFile);
        log.info("originalFilename, {}", originalFilename);

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/files/download/")
                .path(originalFilename)
                .toUriString();

        return new FileDTO(
                originalFilename,
                file.getContentType(),
                fileUrl, file.getSize());
    }

    public ResponseEntity<Object> downloadFile(String fileName) throws Exception {
        Path filePath = fileSystemStorageService.load(fileName);

        byte[] content = Files.readAllBytes(filePath);

        log.info("File downloaded successfully.");

        final ByteArrayResource byteArrayResource = new ByteArrayResource(content);

        return ResponseEntity
                .ok()
                .contentLength(content.length)
                .header("Content-type", "application/octet-stream")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(byteArrayResource);
    }

    public void deleteFile(String fileName) throws Exception {
        log.info("Delete file...........");
    }

}
