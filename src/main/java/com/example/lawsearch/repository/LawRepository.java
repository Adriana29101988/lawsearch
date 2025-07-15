package com.example.lawsearch.repository;

import com.example.lawsearch.model.Law;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LawRepository extends JpaRepository<Law, Integer > {
 // caut dupa titlu
    List<Law> findByTitleContainingIgnoreCase(String title);

    Optional<Law> findById (Integer id);
    //caut legi dupa o anumita versiune
    List<Law> findByVersion(String version);
}
