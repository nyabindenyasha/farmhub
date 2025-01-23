package com.xplug.tech.filestorage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface StorageStrategy {

    String[] uploadFile(MultipartFile multipartFile) throws Exception;

    ResponseEntity<Object> downloadFile(String fileUrl, HttpServletRequest request) throws Exception;

}
