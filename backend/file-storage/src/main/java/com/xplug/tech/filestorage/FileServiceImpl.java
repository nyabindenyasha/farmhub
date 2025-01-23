package com.xplug.tech.filestorage;

import com.xplug.tech.exception.RecordNotFoundException;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
class FileServiceImpl implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    private final FileRepository fileRepository;

    private final StorageService storageService;

    FileServiceImpl(FileRepository fileRepository, StorageService storageService) {
        this.fileRepository = fileRepository;
        this.storageService = storageService;
    }

    @Override
    public void deleteFile(String fileId) {
        LOGGER.info("Deleting files: {}", fileId);
        File file = findOne(fileId);
        try {
            storageService.deleteFile(file.getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileRepository.deleteById(fileId);
    }


    public File findOne(String fileId) {
        LOGGER.info("Get files: {}", fileId);
        Objects.requireNonNull(fileId, "File id cannot be null");
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new RecordNotFoundException("File was not found"));
    }

    @Override
    public FileDTO getFile(String fileId) {
        File file = findOne(fileId);
        return FileDTO.createFileDto(file);
    }

    @Override
    public List<FileDTO> getAllFiles() {
        List<File> files = fileRepository.findAll();
        List<FileDTO> fileDTOList = new ArrayList<>();
        files.parallelStream().forEach(file -> {
            fileDTOList.add(FileDTO.createFileDto(file));
        });
        return fileDTOList;
    }

    @Override
    public File saveFile(FileDTO fileDTO) {
        LOGGER.info("### SAVING FILE: {}", fileDTO);
        File file = File.createFromFileDTO(fileDTO);
        return fileRepository.save(file);
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Object> downloadFileByFileName(String fileName, HttpServletRequest request) {
        LOGGER.info("Downloading files: {}", fileName);
        return storageService.downloadFile(fileName);
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Object> downloadFileByFileId(String fileId, HttpServletRequest request) {
        LOGGER.info("Downloading files: {}", fileId);
        File file = findOne(fileId);
        return storageService.downloadFile(file.getFileName());
    }

    @SneakyThrows
    @Override
    public FileDTO uploadFile(MultipartFile file) {
        FileDTO uploadedFileDTO = storageService.uploadFile(file);
        File persistedFile = saveFile(uploadedFileDTO);
        return FileDTO.createFileDto(persistedFile);
    }

}
