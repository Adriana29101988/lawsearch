package com.example.lawsearch.service;


import com.example.lawsearch.dto.ArticleDTO;
import com.example.lawsearch.dto.LawDTO;
import com.example.lawsearch.model.Article;
import com.example.lawsearch.model.Law;
import com.example.lawsearch.repository.LawRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Service
public class LawService {

    private final LawRepository lawRepository;

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
        return lawRepository.save(law);
    }

    public void deleteLaw(Integer id) {
        lawRepository.deleteById(id);
    }

    @Transactional
    public LawDTO updateLaw(Integer id, LawDTO lawDTO) {
        Law existingLaw = lawRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Law was not found "));

        existingLaw.setTitle(lawDTO.getTitle());
        existingLaw.setDescription(lawDTO.getDescription());
        existingLaw.setVersion(lawDTO.getVersion());
        existingLaw.setCreatedDate(lawDTO.getCreatedDate());

        List<Article> updatedArticles = lawDTO.getArticles().stream().map(dto -> {
            Article article = new Article();
            article.setId(dto.getId());
            article.setArticleNumber(dto.getArticleNumber());
            article.setContent(dto.getContent());
            article.setLaw(existingLaw);
            return article;
        }).collect(Collectors.toList());

        existingLaw.setArticles(updatedArticles);
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