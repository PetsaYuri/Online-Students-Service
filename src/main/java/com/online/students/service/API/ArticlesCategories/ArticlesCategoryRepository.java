package com.online.students.service.API.ArticlesCategories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesCategoryRepository extends JpaRepository<ArticlesCategoryEntity, Long> {

    ArticlesCategoryEntity findByTitle(String title);
}
