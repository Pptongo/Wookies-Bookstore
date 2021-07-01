package com.wookie.bookstore.controllers;

import java.util.Optional;

import com.wookie.bookstore.requests.PublishBookRequest;
import com.wookie.bookstore.response.ApiResponse;
import com.wookie.bookstore.response.BooksResponse;
import com.wookie.bookstore.service.BookService;
import com.wookie.bookstore.shared.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Manage all the request related with Books.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/api/1.0/books")
@Api(tags = "Books")
public class BooksController {

    /**
     * Inject the Book Service that contains all the business logic.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Autowired
    private BookService bookService;

    /**
     * Get all books from the server.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param title Optiona string to be used to filter the books by Title.
     * @param author Optiona string to be used to filter the books by Author's name.
     * @param description Optiona string to be used to filter the books by Description.
     * @return The @see {@link BookResponse} that contains all the books for the search
     */
    @ApiOperation(value = "Get a list of books.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAll(@RequestParam(value = "title", required = false) Optional<String> title,
        @RequestParam(value = "author", required = false) Optional<String> author, @RequestParam(value = "description", required = false) Optional<String> description) {
        try {
            return new ResponseEntity<>(new ApiResponse(new BooksResponse(BooksResponse.convertToModel(bookService.getAll(title, author, description)))), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create a new book and publish it.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param request The @see {@link PublishBookRequest} with all book parameters.
     * @return The @see {@link BookModel} for created book.
     */
    @ApiOperation(value = "Create a new book.")
    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> publish(@RequestBody PublishBookRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse(bookService.publish(request)), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponeException(e);
        }
    }

    /**
     * Show the details for a book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param id The id of the book who want to see the details
     * @return The @see {@link BookModel} with all the details of the selected book.
     */
    @ApiOperation(value = "Get the details of a book.")
    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> details(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(new ApiResponse(bookService.details(id)), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponeException(e);
        }
    }

    /**
     * Update the book information.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param request The @see {@link PublishBookRequest} used to update the properties of the book.
     * @param id The id of the book who will be updated.
     * @return The @see {@link BookModel} with updated properties for the book.
     */
    @ApiOperation(value = "Update the information of a book.")
    @PutMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> update(@RequestBody PublishBookRequest request, @PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(new ApiResponse(bookService.update(id, request)), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponeException(e);
        }
    }

    /**
     * Delete an existing book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param id The id of the book to be deleted.
     * @return The @see {@link ApiResponse} with the status of the operation.
     */
    @ApiOperation(value = "Delete an existing book.")
    @DeleteMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(new ApiResponse(bookService.delete(id)), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponeException(e);
        }
    }

    /**
     * Manage the object to be responded by the service for any exception.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param e The @see {@link Exception} to be managed.
     * @return The @see {@link ResponseEntity} with the correct @see {@link HttpStatus} and @see {@link ApiResponse} with the failure.
     */
    private ResponseEntity<ApiResponse> manageResponeException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e.getMessage().equals(Constants.Unauthorized)) status = HttpStatus.UNAUTHORIZED;
        if (e.getMessage().equals(Constants.BadRequest)) status = HttpStatus.BAD_REQUEST;
            
        return new ResponseEntity<>(new ApiResponse(e.getMessage()), status);
    }

}