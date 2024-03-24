package com.online.students.service.API.ImageUploading;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class ImageUploadingServiceImpl implements ImageUploadingService {

    @Value("${firebase.properties.bucket_name}")
    private String BUCKET_NAME;
    @Value("${firebase.properties.private_key_filename}")
    private String PRIVATE_KEY_FILENAME;

    @Override
    public File convertToFile(MultipartFile multipartFile, String filename) throws IOException {
        File tempFile = new File(filename);
        try (FileOutputStream fos = new FileOutputStream(tempFile)){
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    @Override
    public void uploadFileOnCloud(File file, String filename) throws IOException {
        BlobId blobId = BlobId.of(BUCKET_NAME, filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

        InputStream inputStream = new FileInputStream("/etc/secrets/" + PRIVATE_KEY_FILENAME);
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        String extension = getExtension(multipartFile.getOriginalFilename());
        String filename = UUID.randomUUID().toString().concat(extension);

        File file = this.convertToFile(multipartFile, filename);
        this.uploadFileOnCloud(file, filename);
        file.delete();
        return filename;
    }

    @Override
    public String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
