package com.example.booklibrary;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BookServiceTest {
    private BookRepository bookRepository = mock(BookRepository.class);
    private BookService bookService = new BookService(bookRepository);


    @Test
    void getBooks(){
      // given
        List<Book> expected = List.of(
                new Book("0345391801", "Java", "Ullenbom"),
                new Book("0345391802", "Java-Skript", "Mueller"),
                new Book("0345391803", "Java", "Ullenbom")
        );
        when(bookRepository.getBooks()).thenReturn(expected);
        // when
        List<Book> actual = bookService.getBooks();
       //then
        assertEquals(expected, actual);

    }

    @Test
    void addBook() {
        // given
        Book book = new Book("0345391804", "Python", "Thomas");

        doNothing().when(bookRepository).addBook(book);
        when(bookRepository.getBook("0345391804")).thenReturn(book);

        //when
        Book actual = bookService.addBook(book);
        //then
        assertEquals(new Book("0345391804", "Python", "Thomas"), actual);

    }

    @Test
    void getBookByIsbn() {
        // given
        String isbn = "0345391801";
        Book expected = new Book("0345391801", "Java", "Ullenbom");

        when(bookRepository.getBook(isbn)).thenReturn(expected);

        //when
        Book actual = bookService.getBook(isbn);

        //then
        assertEquals(expected, actual);
       // assertThat(actual).contains(expected);

    }

    @Test
    void removeBook(){
        // given
        Book book =  new Book("0345391801", "Java", "Ullenbom");

        Book expected = book;
        doNothing().when(bookRepository).removeBook(book);
        when(bookRepository.getBook("0345391801")).thenReturn(null);

        //when
        Book actual = bookService.removeBook(book);

        //then
        assertEquals(expected, actual);
    }


    @Test
    void removeBookByIsbn(){
        // given
        String isbn = "0345391802";
        doNothing().when(bookRepository).removeBookByIsbn(isbn);
        when(bookRepository.getBook(isbn)).thenReturn(null);

        // when
        String actual = bookService.removeBookByIsbn(isbn);

        // then
        String expected = isbn;
        assertEquals(expected, actual);
    }

}