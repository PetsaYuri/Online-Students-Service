package com.online.students.service.API.Articles;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ArticleDTOMapper implements Function<ArticleEntity, ArticleDTO> {

    @Override
    public ArticleDTO apply(ArticleEntity articleEntity) {
        return new ArticleDTO(
                articleEntity.getId(),
                articleEntity.getTitle(),
                articleEntity.getDescription(),
                articleEntity.getDate(),
                articleEntity.getCreator().getId(),
                articleEntity.getArticleCategory().getId());
    }
}
