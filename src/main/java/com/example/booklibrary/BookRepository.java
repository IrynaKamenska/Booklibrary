package com.example.booklibrary;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class BookRepository {
    protected List<Book> books = new ArrayList<>(List.of(
            new Book("0345391801", "Java", "Ullenbom"),
            new Book("0345391802", "Java-Skript", "Mueller"),
            new Book("0345391803", "Java", "Ullenbom")
    ));

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public Book getBook(String isbn) {
        for (Book book : books) {
            if (book.isbn().equals(isbn)) {
                return book;
            }
        }
        throw new NoSuchElementException("No book with isbn " + isbn + " found in this book repo.");
    }


    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book removeBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.isbn().equals(isbn)) {
                books.remove(book);
                return book;
            }
        }
        throw new NoSuchElementException("No book with isbn " + isbn + " found in this book repo.");
    }

}
