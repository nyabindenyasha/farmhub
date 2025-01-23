package com.xplug.tech.filestorage;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FileService {

    void deleteFile(String fileId);

    FileDTO getFile(String fileId);

    List<FileDTO> getAllFiles();

    File saveFile(FileDTO fileDTO);

    ResponseEntity<Object> downloadFileByFileId(String fileId, HttpServletRequest request);

    ResponseEntity<Object> downloadFileByFileName(String fileName, HttpServletRequest request);

    FileDTO uploadFile(MultipartFile file);

}
