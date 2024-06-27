package com.application.courselibrary.service;

import com.application.courselibrary.entity.Author;
import com.application.courselibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author findAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found!"));
    }

    public void createAuthor(@RequestBody Author author) {
        authorRepository.save(author);
    }

    public void deleteAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found!"));
        authorRepository.deleteById(author.getId());
    }

    public void updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Author author1 = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found!"));
        author1.setName(author.getName());
        author1.setDescription(author.getDescription());
        authorRepository.save(author1);
    }
}
