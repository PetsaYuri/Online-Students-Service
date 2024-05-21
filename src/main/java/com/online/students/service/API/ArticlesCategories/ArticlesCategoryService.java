package com.online.students.service.API.ArticlesCategories;

import java.util.List;

public interface ArticlesCategoryService {

    List<ArticlesCategoryEntity> getAll();

    ArticlesCategoryEntity getById(Long id);

    ArticlesCategoryEntity getByTitle(String title);

    ArticlesCategoryEntity create(ArticlesCategoryDTO articlesCategoryDTO);

    ArticlesCategoryEntity update(Long id, ArticlesCategoryDTO articlesCategoryDTO);

    boolean delete(Long id);
}
