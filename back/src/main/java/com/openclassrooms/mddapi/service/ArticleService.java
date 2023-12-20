package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class ArticleService {

    @Autowired
    private final TopicService topicService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final ArticleRepository articleRepository;

    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }

    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> saveArticle(Article article) {
        return Optional.of(articleRepository.save(article));
    }

    public Iterable<Article> getFeed(Long userId) {
        User user = userService.getUser(userId).get();
        List<Number> topicIds = new ArrayList<>();
        for (Topic topic : user.getTopics()) {
            topicIds.add(topic.getId());
        }

        List<Article> userArticles = new ArrayList<>();
        Iterable<Article> articles = articleRepository.findAll();
        for (Article article : articles) {
            if (topicIds.contains(article.getTopic().getId())) {
                userArticles.add(article);
            }
        }
        return userArticles;
    }

}