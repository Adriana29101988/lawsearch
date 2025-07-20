package com.example.lawsearch.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ArticleDTO {

    private Integer id;

    @NotBlank(message = "Article Number cannot be empty")
    private String articleNumber;

    private String content;

}
