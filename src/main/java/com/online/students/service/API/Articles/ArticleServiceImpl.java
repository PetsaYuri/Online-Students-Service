package com.online.students.service.API.Articles;

import com.online.students.service.API.ArticlesCategories.ArticlesCategoryEntity;
import com.online.students.service.API.ArticlesCategories.ArticlesCategoryService;
import com.online.students.service.API.Users.UserEntity;
import com.online.students.service.API.Users.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final UserService userService;

    private final ArticlesCategoryService articlesCategoryService;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService, ArticlesCategoryService articlesCategoryService) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.articlesCategoryService = articlesCategoryService;
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
        UserEntity creator = userService.getOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ArticlesCategoryEntity articlesCategory = articlesCategoryService.getById(articleDTO.articleCategoryId());

        ArticleEntity article = new ArticleEntity(articleDTO.title(), articleDTO.description(), articleDTO.image(), creator, articlesCategory);
        return articleRepository.save(article);
    }

    @Override
    public ArticleEntity update(Long id, ArticleDTO articleDTO) {
        ArticleEntity existingArticle = getById(id);

        existingArticle.setTitle(articleDTO.title());
        existingArticle.setDescription(articleDTO.description());
        existingArticle.setImage(articleDTO.image());
        ArticlesCategoryEntity articlesCategory = articlesCategoryService.getById(articleDTO.articleCategoryId());
        existingArticle.setArticleCategory(articlesCategory);

        return articleRepository.save(existingArticle);
    }

    @Override
    public boolean delete(Long id) {
        articleRepository.deleteById(id);
        return true;
    }
}
