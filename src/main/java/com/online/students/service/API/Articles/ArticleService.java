package com.online.students.service.API.Articles;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArticleService {

    List<ArticleEntity> getAll();

    ArticleEntity getById(Long id);

    ArticleEntity create(ArticleDTO articleDTO);

    String uploadImage(MultipartFile multipartFile) throws IOException;

    boolean delete(Long id);
}
