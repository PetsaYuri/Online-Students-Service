package com.online.students.service.API.Articles;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    public static final String URI_ARTICLE_ID = "/{id}";

    private final ArticleService articleService;

    private final ArticleDTOMapper articleDTOMapper;

    public ArticleController(ArticleService articleService, ArticleDTOMapper articleDTOMapper) {
        this.articleService = articleService;
        this.articleDTOMapper = articleDTOMapper;
    }

    @GetMapping
    public List<ArticleDTO> getAll() {
        return articleService.getAll()
                .stream()
                .map(articleDTOMapper)
                .toList();
    }

    @GetMapping(URI_ARTICLE_ID)
    public ArticleDTO getById(@PathVariable Long id) {
        ArticleEntity article = articleService.getById(id);
        return articleDTOMapper.apply(article);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDTO create(@RequestBody ArticleDTO articleDTO) {
        ArticleEntity article = articleService.create(articleDTO);
        return articleDTOMapper.apply(article);
    }

    @DeleteMapping(URI_ARTICLE_ID)
    public boolean delete(@PathVariable Long id) {
        return articleService.delete(id);
    }
}
