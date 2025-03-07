package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class ArticleService {

    private final TopicService topicService;

    private final UserService userService;

    private final ArticleRepository articleRepository;

    /**
     * Récupère un article à partir de son id
     * @param id
     * @return
     */
    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
    }


    /**
     * Récupère tous les articles
     * @return
     */
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    /**
     * Sauvegarde un article
     * @param article
     */
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    /**
     * Récupère les articles liés aux topics suivis par l'utilisateur
     * @param userId
     */
    public Iterable<Article> getFeed(Long userId) {
        List<Number> topicIds = new ArrayList<>();
        for (Topic topic : userService.getUser(userId).getTopics()) {
            topicIds.add(topic.getId());
        }
        return articleRepository.findByTopicIdIn(topicIds);
    }

}