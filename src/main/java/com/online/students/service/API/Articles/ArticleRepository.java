package com.online.students.service.API.Articles;

import com.online.students.service.API.ArticlesCategories.ArticlesCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    List<ArticleEntity> findByArticleCategory(ArticlesCategoryEntity articlesCategory);
}