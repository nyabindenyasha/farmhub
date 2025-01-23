package com.xplug.tech.filestorage;

import lombok.Data;

import java.util.Objects;

@Data
public class FileDTO {

    private String fileId;
    private String fileName;
    private String contentType;
    private String fileDownloadUri;
    private Long fileSize;

    public FileDTO() {
    }

    public FileDTO(String fileName, String contentType, String fileDownloadUri, Long fileSize) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileDownloadUri = fileDownloadUri;
        this.fileSize = fileSize;
    }

    public FileDTO(String fileId, String fileName, String contentType, String fileDownloadUri, Long fileSize) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileDownloadUri = fileDownloadUri;
        this.fileSize = fileSize;
    }

    public static FileDTO createFileDto(File file) {

        if (Objects.isNull(file)) {
            return null;
        }

        return new FileDTO(file.getId(), file.getFileName(), file.getContentType(), file.getFileDownloadUri(), file.getFileSize());
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", fileDownloadUri='" + fileDownloadUri + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
