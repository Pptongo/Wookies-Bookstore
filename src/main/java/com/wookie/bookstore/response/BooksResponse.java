package com.wookie.bookstore.response;

import java.util.ArrayList;
import java.util.List;

import com.wookie.bookstore.models.BookModel;
import com.wookie.bookstore.persistance.entities.BookEntity;

public class BooksResponse {
    
    private List<BookModel> books;

    public BooksResponse(List<BookModel> books) {
        this.books = books;
    }

    public List<BookModel> getBooks() {
        return books;
    }

    public static List<BookModel> convertToModel(List<BookEntity> entityBooks) {
        List<BookModel> books = new ArrayList<>();

        if (entityBooks != null) {
            entityBooks.forEach(entity -> books.add(new BookModel(entity)));
        }

        return books;
    }

}
