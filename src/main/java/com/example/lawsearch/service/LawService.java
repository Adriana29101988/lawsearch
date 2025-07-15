package com.example.lawsearch.service;


import com.example.lawsearch.dto.ArticleDTO;
import com.example.lawsearch.dto.LawDTO;
import com.example.lawsearch.model.Article;
import com.example.lawsearch.model.Law;
import com.example.lawsearch.repository.LawRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LawService {

    private final LawRepository lawRepository;

    @Autowired
    public LawService(LawRepository lawRepository) {
        this.lawRepository = lawRepository;
    }

    public List<Law> getAllLaws() {
        return lawRepository.findAll();
    }

    public Optional<Law> getLawById(Integer id) {
        return lawRepository.findById(id);
    }

    public Law addLaw(Law law) {
        if (law.getArticles() != null) {
            for (Article article : law.getArticles()) {
                article.setLaw(law);
            }
        }
        return lawRepository.save(law);
    }

    public void deleteLaw(Integer id) {
        lawRepository.deleteById(id);
    }

    @Transactional
    public LawDTO updateLaw(Integer id, LawDTO lawDTO) {
        Law existingLaw = lawRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Law was not found"));

        existingLaw.setTitle(lawDTO.getTitle());
        existingLaw.setDescription(lawDTO.getDescription());
        existingLaw.setVersion(lawDTO.getVersion());
        existingLaw.setCreatedDate(lawDTO.getCreatedDate());


        Map<Integer, Article> existingArticlesMap = existingLaw.getArticles().stream()
                .collect(Collectors.toMap(Article::getId, a -> a));

        List<Article> updatedArticles = new ArrayList<>();

        for (ArticleDTO dto : lawDTO.getArticles()) {
            if (dto.getId() != null && existingArticlesMap.containsKey(dto.getId())) {
                Article article = existingArticlesMap.get(dto.getId());
                article.setArticleNumber(dto.getArticleNumber());
                article.setContent(dto.getContent());
                updatedArticles.add(article);
                existingArticlesMap.remove(dto.getId());
            } else {

                Article article = new Article();
                article.setArticleNumber(dto.getArticleNumber());
                article.setContent(dto.getContent());
                article.setLaw(existingLaw);
                updatedArticles.add(article);
            }
        }

        // Elimină articolele care nu mai există în DTO
        existingLaw.getArticles().clear();
        existingLaw.getArticles().addAll(updatedArticles);

        Law updatedLaw = lawRepository.save(existingLaw);

        return mapToDTO(updatedLaw);
    }


    private LawDTO mapToDTO(Law law) {
        LawDTO dto = new LawDTO();
        dto.setId(law.getId());
        dto.setTitle(law.getTitle());
        dto.setDescription(law.getDescription());
        dto.setVersion(law.getVersion());
        dto.setCreatedDate(law.getCreatedDate());

        List<ArticleDTO> articleDTOs = law.getArticles().stream().map(article -> {
            ArticleDTO a = new ArticleDTO();
            a.setId(article.getId());
            a.setArticleNumber(article.getArticleNumber());
            a.setContent(article.getContent());
            return a;
        }).collect(Collectors.toList());

        dto.setArticles(articleDTOs);
        return dto;
    }
}