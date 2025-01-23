package com.xplug.tech.filestorage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xplug.tech.jpa.BaseEntity;
import lombok.Data;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "files")
public class File extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotBlank(message = "Storage location id is required")
    @Size(max = 255, message = "From must contain not more than 255 characters")
    private String fileName;

    @JsonIgnore
    private String extension;

    private String contentType;

    private String fileDownloadUri;

    private Long fileSize;

    @ElementCollection
    private Map<String, String> additionalInformation = new HashMap<>();

    public static File createFromFileDTO(FileDTO fileDTO) {
        Objects.requireNonNull(fileDTO, "FileDTO cannot be null");
        File file = new File();
        file.setId(UUID.randomUUID().toString());
        file.setFileName(fileDTO.getFileName());
        file.setContentType(fileDTO.getContentType());
        file.setFileDownloadUri(fileDTO.getFileDownloadUri());
        file.setFileSize(fileDTO.getFileSize());
        return file;
    }

    public Map<String, String> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @PostLoad
    void postLoad() {
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/files/download/")
                .path(fileName)
                .toUriString();
        this.fileDownloadUri = fileUrl;
    }

}
