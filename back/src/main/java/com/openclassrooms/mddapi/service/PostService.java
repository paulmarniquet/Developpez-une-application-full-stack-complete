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

    /**
     * Récupère les posts d'un article
     * @param article_id
     * @return
     */
    public Optional<Post[]> getPostsOfArticle(Long article_id) {
        return postRepository.findByArticleId(article_id);
    }

    /**
     * Sauvegarde un post
     * @param post
     * @return
     */
    public Optional<Post> savePost(Post post) {
        return Optional.of(postRepository.save(post));
    }

    /**
     * Récupère tous les posts
     * @return
     */
    public Iterable<Post> getPosts() {
        return postRepository.findAll();
    }
}