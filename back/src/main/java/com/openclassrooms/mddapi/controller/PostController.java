package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.entity.Article;
import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final UserService userService;

    private final ArticleService articleService;

    /**
     * Route qui va récupérer les posts d'un article
     *
     * @param article_id
     * @return
     */
    @GetMapping("/post/{article_id}")
    public ResponseEntity<Optional<Post[]>> getPostsOfArticle(@PathVariable Long article_id) {
        try {
            Optional<Post[]> posts = postService.getPostsOfArticle(article_id);
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Route qui va sauvegarder un nouveau post
     *
     * @param post
     */
    @PostMapping("/post")
    public ResponseEntity<Post> savePost(@RequestBody PostDto post) {
        try {
            Post newPost = new Post();
            newPost.setContent(post.getContent());
            Article article = articleService.getArticle(post.getArticleId()).get();
            newPost.setArticle(article);
            User user = userService.getUser(post.getUserId()).get();
            newPost.setUser(user);
            postService.savePost(newPost);
            return ResponseEntity.ok(newPost);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Route qui va récupérer tous les posts
     *
     * @return Iterable<Post>
     */
    @GetMapping("/posts")
    public ResponseEntity<Iterable<Post>> getPosts() {
        try {
            Iterable<Post> posts = postService.getPosts();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
