package com.application.courselibrary.service;

import com.application.courselibrary.entity.Book;
import com.application.courselibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found!"));
    }

    public void createBook(@RequestBody Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found!"));
        bookRepository.deleteById(book.getId());
    }

    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book book1 = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found!"));
        book1.setName(book.getName());
        book1.setIsbn(book.getIsbn());
        book1.setDescription(book.getDescription());
        bookRepository.save(book1);
    }
}
