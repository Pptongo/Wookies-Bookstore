package com.wookie.bookstore.models;

import java.util.Date;

import com.wookie.bookstore.persistance.entities.BookEntity;

public class BookModel {
    
    private long id;

    private String title;

    private String description;

    private String coverImage;

    private Double price;

    private Date publishDate;

    private AuthorModel author;

    public BookModel(BookEntity book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.coverImage = book.getCoverImage();
        this.price = book.getPrice();
        this.publishDate = book.getPublishDate();
        this.author = new AuthorModel(book.getAuthor());
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public Double getPrice() {
        return price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public AuthorModel getAuthor() {
        return author;
    }

}
