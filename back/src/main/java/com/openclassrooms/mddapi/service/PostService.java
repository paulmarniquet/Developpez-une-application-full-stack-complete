package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.repository.PostRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    public Optional<Post[]> getPostsOfArticle(Long article_id) {
        return postRepository.findByArticleId(article_id);
    }

    public Optional<Post> savePost(Post post) {
        return Optional.of(postRepository.save(post));
    }

    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }
}