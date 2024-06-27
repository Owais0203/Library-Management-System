package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Category;
import com.application.courselibrary.entity.Publisher;
import com.application.courselibrary.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/publishers")
    public String findAllPublishers(Model model) {
        List<Publisher> publishers = publisherService.findAllPublishers();

        // Sorting the categories by ID in ascending order
        publishers.sort(Comparator.comparing(Publisher::getId));

        model.addAttribute("publishers", publishers);
        return "publishers";
    }

    @GetMapping("/remove-publisher/{id}")
    public String deletePublisher(@PathVariable Long id, Model model) {
        publisherService.deletePublisher(id);
        List<Publisher> publishers = publisherService.findAllPublishers();

        // Sorting the categories by ID in ascending order
        publishers.sort(Comparator.comparing(Publisher::getId));

        model.addAttribute("publishers", publishers);
        return "publishers";
    }

    @GetMapping("/update-publisher/{id}")
    public String updatePublisher(@PathVariable Long id, Model model) {
        Publisher publisher = publisherService.findPublisherById(id);
        model.addAttribute("publisher", publisher);
        return "update-publisher";
    }

    @PostMapping("/save-update-publisher/{id}")
    public String saveUpdatePublisher(@PathVariable Long id, Publisher publisher, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "update-publisher";
        }
        publisherService.updatePublisher(id, publisher);
        List<Publisher> publishers = publisherService.findAllPublishers();

        // Sorting the categories by ID in ascending order
        publishers.sort(Comparator.comparing(Publisher::getId));

        model.addAttribute("publishers", publishers);
        return "redirect:/publishers";
    }

    @GetMapping("/add-publisher")
    public String addPublisher(Publisher publisher) {
        return "add-publisher";
    }

    @PostMapping("/save-publisher")
    public String createPublisher(Publisher publisher, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "add-publisher";
        }
        publisherService.createPublisher(publisher);
        List<Publisher> publishers = publisherService.findAllPublishers();

        // Sorting the categories by ID in ascending order
        publishers.sort(Comparator.comparing(Publisher::getId));

        model.addAttribute("publishers", publishers);
        return "redirect:/publishers";
    }
}
