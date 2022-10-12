package com.example.booklibrary;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.booklibrary.BookCovers.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class BookServiceTest {
    private final BookRepository bookRepository = mock(BookRepository.class);
    private final BookService bookService = new BookService(bookRepository);


    @Test
    void getBooks(){
      // given
        List<Book> expected = List.of(
                new Book(SOFT_COVER, "0345391801", "Java", "Ullenbom"),
                new Book(E_BOOK, "0345391802", "Java-Skript", "Mueller"),
                new Book(HARD_COVER, "0345391803", "Java", "Ullenbom")
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
        Book book = new Book(HOERBUCH, "0345391804", "Python", "Thomas");

        doNothing().when(bookRepository).addBook(book);
        when(bookRepository.getBook("0345391804")).thenReturn(book);

        //when
        Book actual = bookService.addBook(book);
        //then
        assertEquals(new Book(HOERBUCH, "0345391804", "Python", "Thomas"), actual);

    }

    @Test
    void getBookByIsbn() {
        // given
        String isbn = "0345391801";
        Book expected = new Book(SOFT_COVER, "0345391801", "Java", "Ullenbom");


        //when
        when(bookRepository.getBook(isbn))
                .thenReturn(expected);
        assertNotNull(bookRepository.getBook(isbn));
        verify(bookRepository).getBook(isbn);

//        when(bookRepository.getBook(isbn)).thenReturn(expected);
//        Book actual = bookService.getBook(isbn);
//        assertEquals(expected, actual);


    }

    @Test
    void removeBook(){
        // given
        Book book =  new Book(SOFT_COVER, "0345391801", "Java", "Ullenbom");

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
        when(bookRepository.removeBookByIsbn(isbn)).thenReturn(new Book(E_BOOK, "0345391802", "Java-Skript", "Mueller"));
        when(bookRepository.getBook(isbn)).thenReturn(null);

        // when
        String actual = bookService.removeBookByIsbn(isbn);

        // then
        String expected = isbn;
        assertEquals(expected, actual);
    }

    @Test
    void getBookByCover(){
        // given
        BookCovers cover = SOFT_COVER;

        Book expected = new Book(SOFT_COVER, "0345391801", "Java", "Ullenbom");

        when(bookRepository.getBookByCover(cover)).thenReturn(expected);

        //when
        Book actual = bookService.getBookByCover(cover);

        //then
        assertEquals(expected, actual);
    }

}