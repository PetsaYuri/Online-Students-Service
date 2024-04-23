package com.online.students.service.API.ArticlesCategories;

import com.online.students.service.API.Articles.ArticleDTO;

import java.util.List;

public record ArticlesCategoryDTO(
        Long id,
        String title,
        String description,
        List<ArticleDTO> articleDTOList) {}
