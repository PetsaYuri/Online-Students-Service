package com.online.students.service.API.ArticlesCategories;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories-of-articles")
public class ArticlesCategoryController {

    public static final String URI_ARTICLES_CATEGORIES_ID = "/{id}";

    private final ArticlesCategoryService articlesCategoryService;

    private final ArticlesCategoryDTOMapper articlesCategoryDTOMapper;

    public ArticlesCategoryController(ArticlesCategoryService articlesCategoryService, ArticlesCategoryDTOMapper articlesCategoryDTOMapper) {
        this.articlesCategoryService = articlesCategoryService;
        this.articlesCategoryDTOMapper = articlesCategoryDTOMapper;
    }

    @GetMapping
    public List<ArticlesCategoryDTO> getAll() {
        return articlesCategoryService.getAll()
                .stream()
                .map(articlesCategoryDTOMapper)
                .toList();
    }

    @GetMapping(URI_ARTICLES_CATEGORIES_ID)
    public ArticlesCategoryDTO getById(@PathVariable Long id) {
        ArticlesCategoryEntity articlesCategory = articlesCategoryService.getById(id);
        return articlesCategoryDTOMapper.apply(articlesCategory);
    }

    @PostMapping
    public ArticlesCategoryDTO create(@RequestBody @Valid ArticlesCategoryDTO articlesCategoryDTO) {
        ArticlesCategoryEntity articlesCategory = articlesCategoryService.create(articlesCategoryDTO);
        return articlesCategoryDTOMapper.apply(articlesCategory);
    }

    @PutMapping(URI_ARTICLES_CATEGORIES_ID)
    public ArticlesCategoryDTO update(@PathVariable Long id, @RequestBody @Valid ArticlesCategoryDTO articlesCategoryDTO) {
        ArticlesCategoryEntity articlesCategory = articlesCategoryService.update(id, articlesCategoryDTO);
        return articlesCategoryDTOMapper.apply(articlesCategory);
    }

    @DeleteMapping(URI_ARTICLES_CATEGORIES_ID)
    public boolean delete(@PathVariable Long id) {
        return articlesCategoryService.delete(id);
    }
}
