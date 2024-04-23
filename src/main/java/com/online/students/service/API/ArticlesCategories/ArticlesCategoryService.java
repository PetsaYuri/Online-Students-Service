package com.online.students.service.API.ArticlesCategories;

import java.util.List;

public interface ArticlesCategoryService {

    List<ArticlesCategoryEntity> getAll();

    ArticlesCategoryEntity getById(Long id);

    ArticlesCategoryEntity create(ArticlesCategoryDTO articlesCategoryDTO);

    boolean delete(Long id);
}
