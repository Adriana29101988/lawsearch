package com.example.lawsearch.repository;

import com.example.lawsearch.model.Law;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawRepository extends JpaRepository<Law, Long> {

    List<Law> findByTitleContainingIgnoreCase(String title);
    List<Law> findByVersion(String version);
}
