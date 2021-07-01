package com.wookie.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import com.wookie.bookstore.models.BookModel;
import com.wookie.bookstore.persistance.entities.BookEntity;
import com.wookie.bookstore.persistance.entities.UserEntity;
import com.wookie.bookstore.persistance.repository.BookRepository;
import com.wookie.bookstore.persistance.repository.UserRepository;
import com.wookie.bookstore.requests.PublishBookRequest;
import com.wookie.bookstore.service.BookService;
import com.wookie.bookstore.shared.Constants;
import com.wookie.bookstore.shared.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * The service that contains all the business logic for the Books operations.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Service
public class BookServiceImpl implements BookService {
    
    /**
     * Inject the book repository to run all books database operations.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * Inject the user repository to run all the user database operations.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Get a full or filtered list of books.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param title Optional string with the title to filter the list of books.
     * @param author Optional string with the author's name to filter the list of books.
     * @param description Optional string with the description to filter the list of books.
     * @return A list with the @see {@link BookEntity} that matchs with the search.
     */
    @Override
    public List<BookEntity> getAll(Optional<String> title, Optional<String> author, Optional<String> description) {
        return bookRepository.findFilteredByTitleOrAuthorOrDescripction(title.orElse(""), author.orElse(""), description.orElse(""));
    }

    /**
     * Create a new book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param request The @see {@link PublishBookRequest} with the book's properties.
     * @return The @see {@link BookModel} for the new book.
     * @throws Exception
     */
    @Override
    public BookModel publish(PublishBookRequest request) throws Exception {
        BookModel book = null;

        if (request != null) {
            UserEntity user = getAuthenticatedUser();

            if (user != null) {
                if (user.isDarthVader()) throw new Exception(Messages.DarthVader);

                BookEntity entityBook = new BookEntity(request, user);
                bookRepository.save(entityBook);

                book = new BookModel(entityBook);
            } else {
                throw new Exception(Constants.Unauthorized);
            }
        } else {
            throw new Exception(Constants.BadRequest);
        }

        return book;
    }

    /**
     * Return the details of the book with the passed id.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param id The ID of the book to be returned.
     * @return The @see {@link BookModel} for the found book.
     */
    @Override
    public BookModel details(long id) {
        BookEntity book = bookRepository.findById(id).orElse(null);
        return book != null ? new BookModel(book) : null;
    }

    /**
     * Update the properties of a book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param id The ID of the book to be updated.
     * @param request The @see {@link PublishBookRequest} with the properties to be updated.
     * @return The @see {@link BookModel} with updated properties.
     * @throws Exception
     */
    @Override
    public BookModel update(long id, PublishBookRequest request) throws Exception {
        BookModel book = null;

        if (request != null) {
            UserEntity user = getAuthenticatedUser();

            if (user != null) {
                BookEntity savedBook = bookRepository.findById(id).orElse(null);

                if (savedBook != null) {
                    if (savedBook.getAuthor().getId() == user.getId()) {
                        savedBook.setTitle(request.getTitle());
                        savedBook.setDescription(request.getDescription());
                        savedBook.setCoverImage(request.getCoverImage());
                        savedBook.setPrice(request.getPrice());
                        
                        bookRepository.save(savedBook);
                        
                        book = new BookModel(savedBook);
                    } else {
                        throw new Exception(Messages.UnauthorizedAccessToTheBook);
                    }
                } else {
                    throw new Exception(Messages.BookNotExists);
                }
            } else {
                throw new Exception(Constants.Unauthorized);
            }
        } else {
            throw new Exception(Constants.BadRequest);
        }
        
        return book;
    }

    /**
     * Delete a book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.
     * @param id The ID of the book to be deleted.
     * @return The status of the deletion as boolean.
     * @throws Exception
     */
    @Override
    public Boolean delete(long id) throws Exception {
        BookEntity savedBook = bookRepository.findById(id).orElse(null);
        Boolean deleted = false;

        if (savedBook != null) {
            UserEntity user = getAuthenticatedUser();

            if (user != null) {
                if (savedBook.getAuthor().getId() == user.getId()) {
                    bookRepository.delete(savedBook);
                    deleted = true;
                } else {
                    throw new Exception(Messages.UnauthorizedAccessToTheBook);
                }
            } else {
                throw new Exception(Constants.Unauthorized);
            }
        } else {
            throw new Exception(Messages.BookNotExists);
        }

        return deleted;
    }

    /**
     * Get the authenticated user to know who are running the operations.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @return The @see {@link UserEntity} for the authenticated service.
     */
    private UserEntity getAuthenticatedUser() {
        UserEntity user = null;
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            if (userDetails != null) {
                user = userRepository.findByUsername(userDetails.getUsername());
            }
        }

        return user;
    }

}
