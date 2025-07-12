package com.example.lawsearch.controller;

import com.example.lawsearch.model.Article;
import com.example.lawsearch.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Optional<Article> getArticleById(@PathVariable Integer id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/law/{lawId}")
    public List<Article> getArticlesByLawId(@PathVariable Integer lawId) {
        return articleService.getArticlesByLawId(lawId);
    }

    @PostMapping
    public Article addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Integer id) {
        articleService.deleteArticle(id);
    }
}
