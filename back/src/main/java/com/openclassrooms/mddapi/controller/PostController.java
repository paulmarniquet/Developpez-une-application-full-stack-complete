package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private final PostService postService;

    @GetMapping("/post/{id}")
    public Optional<Post> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/posts")
    public Iterable<Post> getPosts() {
        return postService.getPosts();
    }
}
