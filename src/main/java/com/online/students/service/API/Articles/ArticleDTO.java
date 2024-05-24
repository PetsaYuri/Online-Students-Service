package com.online.students.service.API.Articles;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record ArticleDTO(
        Long id,
        @NotNull @NotBlank String title,
        @NotNull @NotBlank String description,
        @NotNull @NotBlank String image,
        LocalDateTime time,
        Long creatorId,
        @NotNull @Min(1) @Max(999) Long articleCategoryId) {}
