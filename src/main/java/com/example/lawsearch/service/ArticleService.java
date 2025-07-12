package com.example.lawsearch.service;

import com.example.lawsearch.model.Article;
import com.example.lawsearch.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticleById(Integer id) {
        return articleRepository.findById(id);
    }

    public List<Article> getArticlesByLawId(Integer lawId) {
        return articleRepository.findByLawId(lawId);
    }

    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }

    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }
}
