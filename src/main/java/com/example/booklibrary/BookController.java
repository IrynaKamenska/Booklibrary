package com.example.booklibrary;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
public class BookController {

    private final BookService bookService;

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

    @GetMapping(path ="/books/book/{cover}")
    public Book getBook(@PathVariable BookCovers cover){
        return bookService.getBookByCover(cover);
    }

    @PostMapping("/books/book")
    public Book addBook(@RequestBody Book book){
         bookService.addBook(book);
         return book;
    }


    @DeleteMapping("/books")
    public Book removeBook(@RequestBody Book book){
        return bookService.removeBook(book);
    }

    @DeleteMapping(path="/books/{isbn}")
    public String removeBookByIsbn(@PathVariable String isbn){
         bookService.removeBookByIsbn(isbn);
         return isbn;
    }
}
