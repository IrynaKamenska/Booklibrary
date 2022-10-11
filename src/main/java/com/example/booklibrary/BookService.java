package com.example.booklibrary;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getBooks(){
        return bookRepo.getBooks();
    }
    public Book getBook(String isbn){
        return bookRepo.getBook(isbn);
    }

    public Book addBook(Book book){
        bookRepo.addBook(book);
        return book;
    }
    public Book removeBook(Book book){
        bookRepo.removeBook(book);
        return book;
    }

    public String removeBookByIsbn(String isbn) {
        bookRepo.removeBookByIsbn(isbn);
        return isbn;
    }

    public Book getBookByCover(BookCovers bookCover){
        return bookRepo.getBookByCover(bookCover);
    }
}
