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

    public Optional<Topic> getTopic(Long id) {
        return topicRepository.findById(id);
    }

    public Iterable<Topic> getTopics() {
        return topicRepository.findAll();
    }
}