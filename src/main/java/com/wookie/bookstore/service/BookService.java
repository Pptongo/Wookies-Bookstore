package com.wookie.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.wookie.bookstore.models.BookModel;
import com.wookie.bookstore.persistance.entities.BookEntity;
import com.wookie.bookstore.requests.PublishBookRequest;

/**
 * Set the allowed methods for Books service.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public interface BookService {
    
    /**
     * Get a full list of all books.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @return A list of @see {@link BookEntity}.
     */
    public List<BookEntity> getAll(Optional<String> title, Optional<String> author, Optional<String> description);

    /**
     * Publish a new book.
     * @param request @see {@link PublishBookRequest} with the paremeters to add a new book.
     * @return The @see {@link BookModel} with the information of the new book.
     * @throws Exception
     */
    public BookModel publish(PublishBookRequest request) throws Exception;

    /**
     * Show the details for a book.
     * @param id The id of the book.
     * @return The @see {@link BookModel} with the details of the book.
     */
    public BookModel details(long id);

    /**
     * Update an existing book.
     * @param id The id of the book.
     * @param request The @see {@link PublishBookRequest} to be used for the updated.
     * @return The @see {@link BookModel} with the udpated properties.
     * @throws Exception
     */
    public BookModel update(long id, PublishBookRequest request) throws Exception;

    /**
     * Delete a book for given id.
     * @param id The id of the book to be deleted.
     * @return The status of the deletion.
     * @throws Exception
     */
    public Boolean delete(long id) throws Exception;

}
