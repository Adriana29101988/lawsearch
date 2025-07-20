package com.example.lawsearch.controller;

import com.example.lawsearch.dto.LawDTO;
import com.example.lawsearch.model.Law;
import com.example.lawsearch.service.LawService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Laws", description =  "Manage law ")
@RestController
@RequestMapping("/api/laws")
public class LawController {
    private final LawService lawService;

    public LawController(LawService lawService) {
        this.lawService = lawService;
    }

    @GetMapping
    public ResponseEntity<List<Law>> getAllLaws() {
        return ResponseEntity.ok(lawService.getAllLaws());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Law> getLawById(@PathVariable Integer id) {
        Optional<Law> law = lawService.getLawById(id);
        if (law.isPresent()) {
            return ResponseEntity.ok(law.get());
        } else {
            return ResponseEntity.notFound().build();
        }

//        return lawService.getLawById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
    }

    @Operation (summary = "create a new law")
    @PreAuthorize("hasRole('Admin)")
    @PostMapping
    public ResponseEntity<Law> addLaw(@RequestBody Law law) {
        return ResponseEntity.ok(lawService.addLaw(law));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaw(@PathVariable Integer id) {
        lawService.deleteLaw(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LawDTO> updateLaw(@PathVariable Integer id, @RequestBody LawDTO lawDTO) {
        return ResponseEntity.ok(lawService.updateLaw(id, lawDTO));
    }
}
