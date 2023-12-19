package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.TopicService;
import com.openclassrooms.mddapi.service.UserService;
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

    @Autowired
    private final TopicService topicService;

    @GetMapping("/article/{id}")
    public Optional<Article> getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping("/articles")
    public Iterable<Article> getArticles() {
        return articleService.getArticles();
    }

    @PostMapping("/articles")
    public Optional<Article> saveArticle(@RequestBody ArticleDto article) {
        System.out.println(article);
        Article newArticle = new Article();
        newArticle.setTitle(article.getTitle());
        newArticle.setContent(article.getContent());
        newArticle.setTopic(topicService.getTopic(article.getTopicId()).get());
        System.out.println(newArticle);
        return articleService.saveArticle(newArticle);
    }
}
