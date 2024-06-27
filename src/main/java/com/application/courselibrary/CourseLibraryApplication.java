package com.application.courselibrary;

import com.application.courselibrary.entity.Author;
import com.application.courselibrary.entity.Book;
import com.application.courselibrary.entity.Category;
import com.application.courselibrary.entity.Publisher;
import com.application.courselibrary.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourseLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseLibraryApplication.class, args);
	}

	@Bean
	public CommandLineRunner initialCreate(BookService bookService) {
		return (args) -> {
			Book book1 = new Book("ABC1", "Book name1", "My first book");
			Author author1 = new Author("Test name1", "Test description1");
			Category category1 = new Category("Business books");
			Publisher publisher1 = new Publisher("First Publisher");
			book1.addAuthor(author1);
			book1.addCategory(category1);
			book1.addPublisher(publisher1);
			bookService.createBook(book1);

			Book book2 = new Book("ABC2", "Book name2", "My Second book");
			Author author2 = new Author("Test name2", "Test description2");
			Category category2 = new Category("Science books");
			Publisher publisher2 = new Publisher("Second Publisher");
			book2.addAuthor(author2);
			book2.addCategory(category2);
			book2.addPublisher(publisher2);
			bookService.createBook(book2);

			Book book3 = new Book("ABC3", "Book name3", "My third book");
			Author author3 = new Author("Test name3", "Test description3");
			Category category3 = new Category("Islamic books");
			Publisher publisher3 = new Publisher("Third Publisher");
			book3.addAuthor(author3);
			book3.addCategory(category3);
			book3.addPublisher(publisher3);
			bookService.createBook(book3);

			Book book4 = new Book("ABC4", "Book name4", "My forth book");
			Author author4 = new Author("Test name4", "Test description4");
			Category category4 = new Category("Medical books");
			Publisher publisher4 = new Publisher("forth Publisher");
			book4.addAuthor(author4);
			book4.addCategory(category4);
			book4.addPublisher(publisher4);
			bookService.createBook(book4);

			Book book5 = new Book("ABC5", "Book name5", "My fifth book");
			Author author5 = new Author("Test name5", "Test description5");
			Category category5 = new Category("Non-medical books");
			Publisher publisher5 = new Publisher("Fifth Publisher");
			book5.addAuthor(author5);
			book5.addCategory(category5);
			book5.addPublisher(publisher5);
			bookService.createBook(book5);
		};
	}

}
