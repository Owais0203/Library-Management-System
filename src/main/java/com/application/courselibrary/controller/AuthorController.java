package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Author;
import com.application.courselibrary.entity.Book;
import com.application.courselibrary.service.AuthorService;
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
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        List<Author> authors = authorService.findAllAuthors();

        // Sorting the categories by ID in ascending order
        authors.sort(Comparator.comparing(Author::getId));

        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/author/{id}")
    public String findAuthor(@PathVariable Long id, Model model) {
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        return "list-author";
    }

    @GetMapping("/remove-author/{id}")
    public String deleteAuthor(@PathVariable Long id, Model model) {
        authorService.deleteAuthor(id);
        List<Author> authors = authorService.findAllAuthors();

        // Sorting the categories by ID in ascending order
        authors.sort(Comparator.comparing(Author::getId));

        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable Long id, Model model) {
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        return "update-author";
    }

    @PostMapping("/save-update-author/{id}")
    public String updateAuthor(@PathVariable Long id, Author author, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "update-author";
        }
        authorService.updateAuthor(id, author);
        List<Author> authors = authorService.findAllAuthors();

        // Sorting the categories by ID in ascending order
        authors.sort(Comparator.comparing(Author::getId));

        model.addAttribute("authors", authors);
        return "redirect:/authors";
    }

    @GetMapping("/add-author")
    public String showCreateAuthor(Author author) {
        return "add-author";
    }

    @PostMapping("/save-author")
    public String saveAuthor(Author author, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "add-author";
        }
        authorService.createAuthor(author);
        List<Author> authors = authorService.findAllAuthors();

        // Sorting the categories by ID in ascending order
        authors.sort(Comparator.comparing(Author::getId));

        model.addAttribute("authors", authors);
        return "redirect:/authors";
    }
}
