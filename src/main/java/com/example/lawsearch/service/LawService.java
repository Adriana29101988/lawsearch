package com.example.lawsearch.service;

import com.example.lawsearch.model.Law;
import com.example.lawsearch.repository.LawRepository;
import org.springframework.stereotype.Service;

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

    public Optional<Law> getLawById(Long id) {
        return lawRepository.findById(id);
    }

    public List<Law> searchLawsByTitle(String title) {
        return lawRepository.findByTitleContainingIgnoreCase(title);
    }

    public Law addLaw(Law law) {
        return lawRepository.save(law);
    }

    public void deleteLaw(Long id) {
        lawRepository.deleteById(id);
    }
}
