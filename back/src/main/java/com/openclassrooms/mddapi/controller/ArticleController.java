package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.TopicService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    private final TopicService topicService;

    private final UserService userService;

    /**
     * Route qui va récupérer un article à partir de son id
     *
     * @param id
     * @return Article
     */
    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        try {
            Article article = articleService.getArticle(id);
            return ResponseEntity.ok(article);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Route qui va récupérer les articles d'un utilisateur
     *
     * @param userId
     * @return Iterable<Article>
     */
    @GetMapping("/feed/{userId}")
    public ResponseEntity<Iterable<Article>> getFeed(@PathVariable Long userId) {
        try {
            Iterable<Article> articles = articleService.getFeed(userId);
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Route qui va récupérer tous les articles
     *
     * @return Iterable<Article>
     */
    @GetMapping("/articles")
    public ResponseEntity<Iterable<Article>> getArticles() {
        try {
            Iterable<Article> articles = articleService.getArticles();
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Route qui va enregister un nouvel article
     *
     * @param article
     * @return Nouvel Article
     */
    @PostMapping("/articles")
    public ResponseEntity<String> saveArticle(@RequestBody @Valid ArticleDto article) {
        try {
            Article newArticle = new Article();
            newArticle.setTitle(article.getTitle());
            newArticle.setContent(article.getContent());
            newArticle.setTopic(topicService.getTopic(article.getTopicId()));
            newArticle.setUser(userService.getUser(article.getUserId()));
            articleService.saveArticle(newArticle);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
