package com.online.students.service.API.ImageUploading;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ImageUploadingController {

    private final ImageUploadingService imageUploadingService;

    public ImageUploadingController(ImageUploadingService imageUploadingService) {
        this.imageUploadingService = imageUploadingService;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) throws IOException {
        String filename = imageUploadingService.upload(image);
        Map<String, String> response = new HashMap<>();
        response.put("filename", filename);
        return ResponseEntity.ok(response);
    }
}
