package com.example.lawsearch.controller;

import com.example.lawsearch.dto.LawDTO;
import com.example.lawsearch.model.Law;
import com.example.lawsearch.service.LawService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laws")
public class LawController {
    private final LawService lawService;

    public LawController(LawService lawService) {
        this.lawService = lawService;
    }

    @GetMapping
    public List<Law> getAllLaws() {
        return lawService.getAllLaws();
    }

    @GetMapping("/{id}")
    public Optional<Law> getLawById(@PathVariable Integer id) {
        return lawService.getLawById(id);
    }

    @PostMapping
    public Law addLaw(@RequestBody Law law) {
        return lawService.addLaw(law);
    }

    @DeleteMapping("/{id}")
    public void deleteLaw(@PathVariable Integer id) {
        lawService.deleteLaw(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LawDTO> updateLaw(@PathVariable Integer id, @Valid @RequestBody LawDTO lawDTO) {
        LawDTO updatedLaw = lawService.updateLaw(id, lawDTO);
        return ResponseEntity.ok(updatedLaw);
    }
}
