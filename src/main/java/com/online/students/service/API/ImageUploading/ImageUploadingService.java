package com.online.students.service.API.ImageUploading;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageUploadingService {
    File convertToFile(MultipartFile multipartFile, String filename) throws IOException;

    void uploadFileOnCloud(File file, String filename) throws IOException;

    String upload(MultipartFile multipartFile);

    String getExtension(String filename);
}
