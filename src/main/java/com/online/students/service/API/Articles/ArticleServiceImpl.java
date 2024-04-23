package com.online.students.service.API.Articles;

import com.online.students.service.API.ArticlesCategories.ArticlesCategoryEntity;
import com.online.students.service.API.ArticlesCategories.ArticlesCategoryService;
import com.online.students.service.API.ImageUploading.ImageUploadingService;
import com.online.students.service.API.Users.UserEntity;
import com.online.students.service.API.Users.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final UserService userService;

    private final ArticlesCategoryService articlesCategoryService;

    private final ImageUploadingService imageUploadingService;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService, ArticlesCategoryService articlesCategoryService,
                              ImageUploadingService imageUploadingService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.articlesCategoryService = articlesCategoryService;
        this.imageUploadingService = imageUploadingService;
    }

    @Override
    public List<ArticleEntity> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public ArticleEntity getById(Long id) {
        return articleRepository.getReferenceById(id);
    }

    @Override
    public ArticleEntity create(ArticleDTO articleDTO) {
        UserEntity creator = userService.getOneById(articleDTO.creatorId());
        ArticlesCategoryEntity articlesCategory = articlesCategoryService.getById(articleDTO.articleCategoryId());

        ArticleEntity article = new ArticleEntity(articleDTO.title(), articleDTO.description(), articleDTO.image(), creator, articlesCategory);
        return articleRepository.save(article);
    }

    @Override
    public String uploadImage(MultipartFile image) throws IOException {
        return imageUploadingService.upload(image);
    }

    @Override
    public boolean delete(Long id) {
        articleRepository.deleteById(id);
        return true;
    }
}
