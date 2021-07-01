package com.wookie.bookstore.response;

import java.util.ArrayList;
import java.util.List;

import com.wookie.bookstore.models.BookModel;
import com.wookie.bookstore.persistance.entities.BookEntity;

/**
 * Represents the Book response with public properties.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class BooksResponse {
    
    /**
     * A list that contains all the @see {@link BookModel}.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private List<BookModel> books;

    /**
     * Create an empty BookResponse.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    public BooksResponse() { }

    /**
     * Create a BookResponse using the give list of @see {@link BookModel}.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param books A list that contains all the @see {@link BookModel}.
     */
    public BooksResponse(List<BookModel> books) {
        this.books = books;
    }

    public List<BookModel> getBooks() { return books; }

    public void setBooks(List<BookModel> books) { this.books = books; }

    /**
     * Convert a list of @see {@link BookEntity} into a list of @see {@link BookModel}.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param entityBooks A list with all the @see {@link BookEntity} to be converted into @see {@link BookModel}.
     * @return The converted book with all the @see {@link BookModel}.
     */
    public static List<BookModel> convertToModel(List<BookEntity> entityBooks) {
        List<BookModel> books = new ArrayList<>();

        if (entityBooks != null) {
            entityBooks.forEach(entity -> books.add(new BookModel(entity)));
        }

        return books;
    }

}
