package com.example.lawsearch.service;

import com.example.lawsearch.model.Article;
import com.example.lawsearch.model.Law;
import com.example.lawsearch.repository.ArticleRepository;
import com.example.lawsearch.repository.LawRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LawService {

    private final LawRepository lawRepository;

    public LawService(LawRepository lawRepository) {
        this.lawRepository = lawRepository;
    }

    // GET all
    public List<Law> getAllLaws() {
        return lawRepository.findAll();
    }

    // GET by ID
    public Optional<Law> getLawById(Integer id) {
        return lawRepository.findById(id);
    }

    // POST
    public Law addLaw(Law law) {
        return lawRepository.save(law);
    }

    // DELETE
    public void deleteLaw(Integer id) {
        lawRepository.deleteById(id);
    }

    // PUT — actualizare doar pe baza DTO
    @Transactional
    public LawDTO updateLaw(Integer id, LawDTO lawDTO) {
        Law law = lawRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Law not found"));

        law.setTitle(lawDTO.getTitle());
        law.setDescription(lawDTO.getDescription());
        law.setVersion(lawDTO.getVersion());
        law.setCreatedDate(lawDTO.getCreatedDate());

        // înlocuim articolele existente cu cele noi din DTO
        law.getArticles().clear();

        if (lawDTO.getArticles() != null) {
            List<Article> updatedArticles = lawDTO.getArticles().stream().map(articleDTO -> {
                Article article = new Article();
                article.setId(articleDTO.getId()); // sau null pentru articole noi
                article.setArticleNumber(articleDTO.getArticleNumber());
                article.setContent(articleDTO.getContent());
                article.setLaw(law); // legătura inversă
                return article;
            }).collect(Collectors.toList());

            law.getArticles().addAll(updatedArticles);
        }

        lawRepository.save(law);

        // reconstruiți DTO pentru răspuns
        LawDTO updatedDTO = new LawDTO();
        updatedDTO.setId(law.getId());
        updatedDTO.setTitle(law.getTitle());
        updatedDTO.setDescription(law.getDescription());
        updatedDTO.setVersion(law.getVersion());
        updatedDTO.setCreatedDate(law.getCreatedDate());

        if (law.getArticles() != null) {
            List<ArticleDTO> articleDTOs = law.getArticles().stream().map(article -> {
                ArticleDTO dto = new ArticleDTO();
                dto.setId(article.getId());
                dto.setArticleNumber(article.getArticleNumber());
                dto.setContent(article.getContent());
                return dto;
            }).collect(Collectors.toList());
            updatedDTO.setArticles(articleDTOs);
        }

        return updatedDTO;
    }
}