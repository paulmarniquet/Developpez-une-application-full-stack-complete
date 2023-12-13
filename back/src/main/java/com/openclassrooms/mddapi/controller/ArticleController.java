package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticleController {

    @Autowired
    private final ArticleService articleService;

    @GetMapping("/article/{id}")
    public Optional<Article> getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping("/articles")
    public Iterable<Article> getArticles() {
        return articleService.getArticles();
    }
}
