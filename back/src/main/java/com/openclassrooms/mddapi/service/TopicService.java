package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class TopicService {

    private final TopicRepository topicRepository;

    /**
     * Récupère un topic à partir de son id
     * @param id
     * @return
     */
    public Topic getTopic(Long id) {
        return topicRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid topic Id:" + id));
    }

    /**
     * Récupère tous les topics
     * @return
     */
    public Iterable<Topic> getTopics() {
        return topicRepository.findAll();
    }
}