package com.wookie.bookstore.persistance.repository;

import com.wookie.bookstore.persistance.entities.BookEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {
    
}
