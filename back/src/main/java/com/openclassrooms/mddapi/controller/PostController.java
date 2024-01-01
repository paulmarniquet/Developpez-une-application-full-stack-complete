package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private final PostService postService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final ArticleService articleService;

    /**
     * Route qui va récupérer les posts d'un article
     * @param article_id
     * @return
     */
    @GetMapping("/post/{article_id}")
    public Optional<Post[]> getPostsOfArticle(@PathVariable Long article_id) {
        return postService.getPostsOfArticle(article_id);
    }

    /**
     * Route qui va sauvegarder un nouveau post
     * @param post
     */
    @PostMapping("/post")
    public Optional<Post> savePost(@RequestBody PostDto post) {
        Post newPost = new Post();
        newPost.setContent(post.getContent());
        Article article = articleService.getArticle(post.getArticleId()).get();
        newPost.setArticle(article);
        User user = userService.getUser(post.getUserId()).get();
        newPost.setUser(user);
        return postService.savePost(newPost);
    }

    /**
     * Route qui va récupérer tous les posts
     * @return Iterable<Post>
     */
    @GetMapping("/posts")
    public Iterable<Post> getPosts() {
        return postService.getPosts();
    }
}
