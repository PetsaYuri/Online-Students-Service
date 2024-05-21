package com.online.students.service.API.ArticlesCategories;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesCategoryServiceImpl implements ArticlesCategoryService {

    private final ArticlesCategoryRepository articlesCategoryRepository;

    public ArticlesCategoryServiceImpl(ArticlesCategoryRepository articlesCategoryRepository) {
        this.articlesCategoryRepository = articlesCategoryRepository;
    }

    @Override
    public List<ArticlesCategoryEntity> getAll() {
        return articlesCategoryRepository.findAll();
    }

    @Override
    public ArticlesCategoryEntity getById(Long id) {
        return articlesCategoryRepository.getReferenceById(id);
    }

    @Override
    public ArticlesCategoryEntity getByTitle(String title) {
        return articlesCategoryRepository.findByTitle(title);
    }

    @Override
    public ArticlesCategoryEntity create(ArticlesCategoryDTO articlesCategoryDTO) {
        ArticlesCategoryEntity articlesCategory = new ArticlesCategoryEntity(articlesCategoryDTO.title(), articlesCategoryDTO.description());
        return articlesCategoryRepository.save(articlesCategory);
    }

    @Override
    public ArticlesCategoryEntity update(Long id, ArticlesCategoryDTO articlesCategoryDTO) {
        ArticlesCategoryEntity existingCategory = getById(id);

        existingCategory.setTitle(articlesCategoryDTO.title());
        existingCategory.setDescription(articlesCategoryDTO.description());

        return articlesCategoryRepository.save(existingCategory);
    }

    @Override
    public boolean delete(Long id) {
        articlesCategoryRepository.deleteById(id);
        return true;
    }
}
