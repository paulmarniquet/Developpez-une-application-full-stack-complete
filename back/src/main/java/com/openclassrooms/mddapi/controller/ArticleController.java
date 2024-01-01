package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.TopicService;
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

    /**
     * Route qui va récupérer un article à partir de son id
     * @param id
     * @return Optional<Article>
     */
    @GetMapping("/article/{id}")
    public Optional<Article> getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    /**
     * Route qui va récupérer les articles d'un utilisateur
     * @param user_id
     * @return Iterable<Article>
     */
    @GetMapping("/feed/{user_id}")
    public Iterable<Article> getFeed(@PathVariable Long user_id) {
        return articleService.getFeed(user_id);
    }

    /**
     * Route qui va récupérer tous les articles
     * @return Iterable<Article>
     */
    @GetMapping("/articles")
    public Iterable<Article> getArticles() {
        return articleService.getArticles();
    }

    /**
     * Route qui va supprimer un article à partir de son id
     * @return Nouvel Article
     */
    @PostMapping("/articles")
    public Optional<Article> saveArticle(@RequestBody ArticleDto article) {
        Article newArticle = new Article();
        newArticle.setTitle(article.getTitle());
        newArticle.setContent(article.getContent());
        newArticle.setTopic(topicService.getTopic(article.getTopicId()).get());
        return articleService.saveArticle(newArticle);
    }
}
