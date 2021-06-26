package com.wookie.bookstore.service;

import java.util.List;

import com.wookie.bookstore.models.BookModel;
import com.wookie.bookstore.persistance.entities.BookEntity;
import com.wookie.bookstore.requests.PublishBookRequest;

/**
 * Set the allowed methods for Books service.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 */
public interface BookService {
    
    /**
     * Get a full list of all books
     * @return
     */
    public List<BookEntity> getAll();

    /**
     * Publish a new book.
     * @param request Request with the paremeters to add a new book.
     * @return
     * @throws Exception
     */
    public BookModel publish(PublishBookRequest request) throws Exception;

    /**
     * Show the details for a book
     * @param id The id of the book
     * @return
     */
    public BookModel details(long id);

    /**
     * Update an existing book
     * @param id The id of the book
     * @param request The parameters to be used for the updated
     * @return
     * @throws Exception
     */
    public BookModel update(long id, PublishBookRequest request) throws Exception;

    /**
     * Delete a book
     * @param id The id of the book
     * @return
     * @throws Exception
     */
    public Boolean delete(long id) throws Exception;

}
