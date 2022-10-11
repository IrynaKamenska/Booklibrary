package com.example.booklibrary;

enum Covers{
    SOFT_COVER,
    E_BOOK,
    HARD_COVER,
    HOERBUCH
}

public record Book (String isbn, String title, String author){
}
