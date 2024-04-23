package com.online.students.service.API.ArticlesCategories;

import com.online.students.service.API.Articles.ArticleDTOMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ArticlesCategoryDTOMapper implements Function<ArticlesCategoryEntity, ArticlesCategoryDTO> {

    private final ArticleDTOMapper articleDTOMapper;

    public ArticlesCategoryDTOMapper(ArticleDTOMapper articleDTOMapper) {
        this.articleDTOMapper = articleDTOMapper;
    }

    @Override
    public ArticlesCategoryDTO apply(ArticlesCategoryEntity articlesCategoryEntity) {
        return new ArticlesCategoryDTO(
                articlesCategoryEntity.getId(),
                articlesCategoryEntity.getTitle(),
                articlesCategoryEntity.getDescription(),
                articlesCategoryEntity.getListOfArticles()
                        .stream()
                        .map(articleDTOMapper)
                        .toList()
        );
    }
}
