package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.service.TopicService;
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
public class TopicController {

    @Autowired
    private final TopicService topicService;

    @GetMapping("/topic/{id}")
    public Optional<Topic> getTopic(@PathVariable Long id) {
        return topicService.getTopic(id);
    }

    @GetMapping("/topics")
    public Iterable<Topic> getTopics() {
        return topicService.getTopics();
    }
}
