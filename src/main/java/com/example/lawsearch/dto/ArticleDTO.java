package com.example.lawsearch.dto;

import jakarta.validation.constraints.NotBlank;

public class ArticleDTO {

    private Integer id;

    @NotBlank(message = "Article Number cannot be empty")
    private String articleNumber;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
