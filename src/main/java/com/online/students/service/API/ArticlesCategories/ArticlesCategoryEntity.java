package com.online.students.service.API.ArticlesCategories;

import com.online.students.service.API.Articles.ArticleEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories_of_articles")
public class ArticlesCategoryEntity {

    public ArticlesCategoryEntity() {}

    public ArticlesCategoryEntity(String title, String description) {
        this.title = title;
        this.description = description;
        listOfArticles = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title, description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "articleCategory")
    private List<ArticleEntity> listOfArticles;

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

    public List<ArticleEntity> getListOfArticles() {
        return listOfArticles;
    }

    public void setListOfArticles(List<ArticleEntity> listOfArticles) {
        this.listOfArticles = listOfArticles;
    }
}
