package com.wookie.bookstore.persistance.repository;

import java.util.List;

import com.wookie.bookstore.persistance.entities.BookEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * All the database operations for @see {@link BooksEntity} are defined here.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {

    /**
     * Return a list of @see {@link BookEntity} filtered by title, author and/or description. If all parameters are empty string: return all books.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param title The title of the book used to filter the full books.
     * @param author The Author's name of the book used to filter the full books.
     * @param description The description of the book used to filter the full books.
     * @return The full or filtered list of @see {@link BookEntity} according with the given parameters.
     */
    @Query("SELECT b FROM BookEntity b WHERE b.title LIKE %:title% AND b.author.firstName LIKE %:author% AND b.description LIKE %:description%")
    List<BookEntity> findFilteredByTitleOrAuthorOrDescripction(String title, String author, String description);
    
}
