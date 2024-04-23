package com.online.students.service.API.Articles;

import com.online.students.service.API.Users.UserDTO;

import java.time.LocalDateTime;

public record ArticleDTO(
        Long id,
        String title,
        String description,
        String image,
        LocalDateTime time,
        Long creatorId,
        Long articleCategoryId) {}
