package com.wookie.bookstore.service;

import java.util.List;

import com.wookie.bookstore.models.BookModel;
import com.wookie.bookstore.persistance.entities.BookEntity;
import com.wookie.bookstore.requests.PublishBookRequest;

public interface BookService {
    
    public List<BookEntity> getAll();

    public BookModel publish(PublishBookRequest request) throws Exception;

    public BookModel update(long id, PublishBookRequest request) throws Exception;

}
