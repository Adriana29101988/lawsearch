package com.example.lawsearch.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LawDTO {
    private Integer id;

    @NotBlank
    private String title;
    private String description;
    private String version;

    @NotNull
    private LocalDate createdDate;
    private List<ArticleDTO> articles;

}