package com.xplug.tech.notifications.email.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

import static java.util.Objects.nonNull;

@Data
public class Attachment {

    private String name;

    private MultipartFile file;

    @JsonIgnore
    private Boolean hasLink;

    public Attachment() {
        this.hasLink = true;
    }

    public Attachment(MultipartFile file) {
        this.file = file;
        name = nonNull(file) ? file.getOriginalFilename()
                : "Attachment_".concat(String.valueOf(new Random().nextInt()));
    }
}
