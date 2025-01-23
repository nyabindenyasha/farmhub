package com.xplug.tech.filestorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("v1/files")
public class FileRestController {

    private final Logger log = LoggerFactory.getLogger(FileRestController.class);

    private final FileService fileService;

    public FileRestController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<FileDTO> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("REST request to upload file");
        FileDTO fileDTO = fileService.uploadFile(file);
        return new ResponseEntity<>(fileDTO, null, HttpStatus.OK);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Object> downloadFileByFileName(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        return fileService.downloadFileByFileName(fileName, request);
    }

    @GetMapping("/{fileId}")
    public FileDTO getFileDetails(@PathVariable String fileId) {
        log.info("Retrieving file details request: {} ", fileId);
        return fileService.getFile(fileId);
    }

    @GetMapping("")
    public List<FileDTO> getFiles() {
        log.info("Retrieving all file details request: {} ");
        return fileService.getAllFiles();
    }

    @DeleteMapping("/{fileId}")
    public void deleteFile(@PathVariable String fileId) {
        log.info("Received delete file request: {} ", fileId);
        fileService.deleteFile(fileId);
    }

    @GetMapping("/{fileId}/download")
    public ResponseEntity<?> downloadFileById(@PathVariable String fileId, HttpServletRequest request) {
        return fileService.downloadFileByFileId(fileId, request);

    }
}

