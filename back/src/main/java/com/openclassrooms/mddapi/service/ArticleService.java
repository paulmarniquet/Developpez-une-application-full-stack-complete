package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ArticleService {

    @Autowired
    private final ArticleRepository articleRepository;

    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }

    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

}