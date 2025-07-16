package com.example.lawsearch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "article_number", nullable = false)
    private String articleNumber;

    @Column(name = "content")
    private String content;

    // Relația cu tabela law
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "law_id")
    @JsonBackReference
    private Law law;


}