package com.application.courselibrary.service;

import com.application.courselibrary.entity.Publisher;
import com.application.courselibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAllPublishers(){
        return publisherRepository.findAll();
    }

    public Publisher findPublisherById(@PathVariable Long id){
        return publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found!"));
    }

    public void createPublisher(@RequestBody Publisher publisher){
        publisherRepository.save(publisher);
    }

    public void updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher){
        Publisher publisher1 = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found!"));
        publisher1.setName(publisher.getName());
        publisherRepository.save(publisher1);
    }

    public void deletePublisher(@PathVariable Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found!"));
        publisherRepository.deleteById(publisher.getId());
    }
}
