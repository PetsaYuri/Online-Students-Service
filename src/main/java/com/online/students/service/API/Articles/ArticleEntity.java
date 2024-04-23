package com.online.students.service.API.Articles;

import com.online.students.service.API.ArticlesCategories.ArticlesCategoryEntity;
import com.online.students.service.API.Users.UserEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
public class ArticleEntity {

    public ArticleEntity() {}

    public ArticleEntity(String title, String description, UserEntity creator, ArticlesCategoryEntity articleCategory) {
        this.title = title;
        this.description = description;
        date = LocalDateTime.now();
        this.creator = creator;
        this.articleCategory = articleCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title, description;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(optional = false)
    private UserEntity creator;

    @ManyToOne(optional = false)
    private ArticlesCategoryEntity articleCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public ArticlesCategoryEntity getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(ArticlesCategoryEntity articleCategory) {
        this.articleCategory = articleCategory;
    }
}
