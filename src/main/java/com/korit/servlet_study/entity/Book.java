package com.korit.servlet_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private int bookId;
    private String bookName;
    // private String isbn;
    private String author;
    private String publisher;
    private String category;
    private String imgUrl;

    // private Author author;
    // private Publisher publisher;
    // private BookCategory bookCategory;
}
