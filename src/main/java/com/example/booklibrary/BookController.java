package com.example.booklibrary;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping(path ="/books/{isbn}")
    public Book getBook(@PathVariable String isbn){
        return bookService.getBook(isbn);
    }

    @PostMapping("/books/book")
    public Book addBook(@RequestBody Book book){
         bookService.addBook(book);
         return book;
    }

    @PostMapping
    public Book removeBook(@RequestBody Book book){
        return bookService.removeBook(book);
    }

    @DeleteMapping(path="books/{isbn}")
    public String removeBookByIsbn(@PathVariable  String isbn){
         bookService.removeBookByIsbn(isbn);
         return isbn;
    }
}
