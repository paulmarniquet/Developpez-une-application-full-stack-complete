package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class TopicService {

    @Autowired
    private final TopicRepository topicRepository;

    /**
     * Récupère un topic à partir de son id
     * @param id
     * @return
     */
    public Optional<Topic> getTopic(Long id) {
        return topicRepository.findById(id);
    }

    /**
     * Récupère tous les topics
     * @return
     */
    public Iterable<Topic> getTopics() {
        return topicRepository.findAll();
    }
}