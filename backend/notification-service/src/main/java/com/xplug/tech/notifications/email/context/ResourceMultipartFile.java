package com.xplug.tech.notifications.email.context;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ResourceMultipartFile implements MultipartFile {

    private final Resource resource;

    public ResourceMultipartFile(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String getName() {
        return resource.getFilename();
    }

    @Override
    public String getOriginalFilename() {
        return resource.getFilename();
    }

    @Override
    public String getContentType() {
        try {
            return resource.getURL().openConnection().getContentType();
        } catch (IOException e) {
            return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }

    @Override
    public boolean isEmpty() {
        try {
            return resource.getInputStream().available() == 0;
        } catch (IOException e) {
            return true;
        }
    }

    @Override
    public long getSize() {
        try {
            return resource.contentLength();
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public byte[] getBytes() throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return resource.getInputStream();
    }

    @Override
    public Resource getResource() {
        return MultipartFile.super.getResource();
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }

    @Override
    public void transferTo(java.nio.file.Path destination) throws IOException, IllegalStateException {
        resource.getFile().toPath().toFile().renameTo(destination.toFile());
    }
}