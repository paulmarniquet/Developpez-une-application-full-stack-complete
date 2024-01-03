package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    /**
     * Route qui va récupérer un topic à partir de son id
     *
     * @param id
     * @return
     */
    @GetMapping("/topic/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicService.getTopic(id);
        return optionalTopic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Route qui va récupérer tous les topics
     *
     * @return Iterable<Topic>
     */
    @GetMapping("/topics")
    public ResponseEntity<Iterable<Topic>> getTopics() {
        try {
            Iterable<Topic> topics = topicService.getTopics();
            return ResponseEntity.ok(topics);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
