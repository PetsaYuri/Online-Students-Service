package com.online.students.service.API.ArticlesCategories;

import com.online.students.service.API.Articles.ArticleDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ArticlesCategoryDTO(
        Long id,
        @NotNull @NotBlank String title,
        @NotNull @NotBlank String description,
        List<ArticleDTO> articleDTOList) {}
