package com.nnroad.common.core.domain;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;

/**
 * Custom MultipartFile implementation to create MultipartFile from bytes
 */
public class MultipartFileFromBytes implements MultipartFile {
    private byte[] bytes;
    private String fileName;
    private String originalFileName;
    private String contentType;
    private FileOutputStream fileOutputStream;

    public MultipartFileFromBytes(byte[] bytes, String fileName, String originalFileName, String contentType) {
        this.bytes = bytes;
        this.fileName = fileName;
        this.contentType = contentType;
        this.originalFileName = originalFileName;
    }

    @Override
    public Resource getResource() {
        return MultipartFile.super.getResource();
    }

    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {
        MultipartFile.super.transferTo(dest);
    }

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getOriginalFilename() {
        return originalFileName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return bytes == null || bytes.length == 0;
    }

    @Override
    public long getSize() {
        return bytes.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return bytes;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        fileOutputStream = new FileOutputStream(dest);
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}


