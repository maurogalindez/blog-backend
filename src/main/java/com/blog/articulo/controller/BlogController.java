package com.blog.articulo.controller;

import com.blog.articulo.model.Article;
import com.blog.articulo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import javax.validation.Valid;
import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @PostMapping("/articles")
    public Article createArticle(@Valid @RequestBody Article article) {
        return articleRepository.save(article);
    }

    @GetMapping("/articles/{id}")
    public Article getArticleById(@PathVariable(value = "id") Long articleId) {
        return articleRepository.findById(articleId).get();
    }

    @PutMapping("/articles/{id}")
    public Article updateArticle(@PathVariable(value = "id") Long articleId,
                                           @Valid @RequestBody Article articleDetails) {

        Article article = articleRepository.findById(articleId).get();

        article.setTitle(articleDetails.getTitle());
        article.setContent(articleDetails.getContent());

        Article updatedArticle = articleRepository.save(article);
        return updatedArticle;
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable(value = "id") Long articleId) {
        Article article = articleRepository.findById(articleId).get();

        articleRepository.delete(article);

        return ResponseEntity.ok().build();
    }

    
}
