package com.example.lawsearch.repository;

import com.example.lawsearch.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByLawId(Long lawId);
    List<Article> findByContentContainingIgnoreCase(String content);

}
