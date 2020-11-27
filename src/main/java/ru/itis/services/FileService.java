package ru.itis.services;

import ru.itis.models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public interface FileService {
    void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size);
    void writeFileFromStorage(Long fileId, OutputStream outputStream);
    Optional<FileInfo> getFileInfo(Long fileId);

}
