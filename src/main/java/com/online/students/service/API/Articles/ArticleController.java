package com.online.students.service.API.Articles;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    public static final String URI_ARTICLE_ID = "/{id}";
    public static final String URI_UPLOAD_IMAGE = "/uploadImage";

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

    @PostMapping(URI_UPLOAD_IMAGE)
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile image) throws IOException {
        String filename = articleService.uploadImage(image);
        Map<String, String> response = new HashMap<>();
        response.put("filename", filename);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(URI_ARTICLE_ID)
    public boolean delete(@PathVariable Long id) {
        return articleService.delete(id);
    }
}
