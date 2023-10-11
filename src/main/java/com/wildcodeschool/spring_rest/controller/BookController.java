package com.wildcodeschool.spring_rest.controller;

import com.wildcodeschool.spring_rest.entity.Book;
import com.wildcodeschool.spring_rest.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> index() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable Long id){
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("title");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable Long id){
        bookRepository.deleteById(id);
        return true;
    }
}
