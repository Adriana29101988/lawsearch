package com.example.lawsearch.service;

import com.example.lawsearch.model.Article;
import com.example.lawsearch.model.Law;
import com.example.lawsearch.repository.LawRepository;
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

        public List<Law> getAllLaws() {
            return lawRepository.findAll();
        }

        public Optional<Law> getLawById(Integer id) {
            return lawRepository.findById(id);
        }

        public List<Law> searchLawsByTitle(String title) {
            return lawRepository.findByTitleContainingIgnoreCase(title);
        }

        public Law addLaw(Law law) {
            for (Article article : law.getArticles()) {
                article.setLaw(law);
            }
            return lawRepository.save(law);
        }

        public void deleteLaw(Integer id) {
            lawRepository.deleteById(id);
        }
    public Optional<Law> updateLaw(Integer id, Law updatedLaw) {
        return lawRepository.findById(id)
                .map(existingLaw -> {
                    existingLaw.setTitle(updatedLaw.getTitle());
                    existingLaw.setDescription(updatedLaw.getDescription());
                    existingLaw.setVersion(updatedLaw.getVersion());
                    existingLaw.setCreatedDate(updatedLaw.getCreatedDate());
                    existingLaw.setArticles(updatedLaw.getArticles());
                    return lawRepository.save(existingLaw);
                });
    }
}
