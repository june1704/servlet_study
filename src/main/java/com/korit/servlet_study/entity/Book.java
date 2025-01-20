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
    private int bookID;
    private String bookName;
    private String isbn;
    private int authorID;
    private int publisherID;
    private int categoryID;
    private String bookImgUrl;

    private Author author;
    private Publisher publisher;
    private BookCategory bookCategory;
}

