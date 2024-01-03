package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.TopicService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
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
     * @return Optional<Article>
     */
    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Optional<Article> optionalArticle = articleService.getArticle(id);
        return optionalArticle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Route qui va récupérer les articles d'un utilisateur
     *
     * @param user_id
     * @return Iterable<Article>
     */
    @GetMapping("/feed/{user_id}")
    public ResponseEntity<Iterable<Article>> getFeed(@PathVariable Long user_id) {
        Iterable<Article> articles = articleService.getFeed(user_id);
        return ResponseEntity.ok(articles);
    }

    /**
     * Route qui va récupérer tous les articles
     *
     * @return Iterable<Article>
     */
    @GetMapping("/articles")
    public ResponseEntity<Iterable<Article>> getArticles() {
        Iterable<Article> articles = articleService.getArticles();
        return ResponseEntity.ok(articles);
    }

    /**
     * Route qui va enregister un nouvel article
     * @param article
     * @return Nouvel Article
     */
    @PostMapping("/articles")
    public ResponseEntity<Article> saveArticle(@RequestBody ArticleDto article) {
        try {
            Article newArticle = new Article();
            newArticle.setTitle(article.getTitle());
            newArticle.setContent(article.getContent());
            newArticle.setTopic(topicService.getTopic(article.getTopicId()).get());
            newArticle.setUser(userService.getUser(article.getUserId()).get());
            articleService.saveArticle(newArticle);
            return ResponseEntity.ok(newArticle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
