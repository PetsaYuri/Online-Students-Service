package com.online.students.service.API.Articles;

import java.util.List;

public interface ArticleService {

    List<ArticleEntity> getAll();

    ArticleEntity getById(Long id);

    ArticleEntity create(ArticleDTO articleDTO);

    ArticleEntity update(Long id, ArticleDTO articleDTO);

    boolean delete(Long id);
}
