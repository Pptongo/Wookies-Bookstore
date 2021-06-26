package com.wookie.bookstore.controllers;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/1.0/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAll() {
        try {
            return new ResponseEntity<>(new ApiResponse(new BooksResponse(BooksResponse.convertToModel(bookService.getAll()))), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> publish(@RequestBody PublishBookRequest request) {
        try {
            return new ResponseEntity<>(new ApiResponse(bookService.publish(request)), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponeException(e);
        }
    }

    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> details(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(new ApiResponse(bookService.details(id)), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponeException(e);
        }
    }

    @PutMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> update(@RequestBody PublishBookRequest request, @PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(new ApiResponse(bookService.update(id, request)), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponeException(e);
        }
    }

    @DeleteMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(new ApiResponse(bookService.delete(id)), HttpStatus.OK);
        } catch (Exception e) {
            return manageResponeException(e);
        }
    }

    private ResponseEntity<ApiResponse> manageResponeException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e.getMessage().equals(Constants.Unauthorized)) status = HttpStatus.UNAUTHORIZED;
        if (e.getMessage().equals(Constants.BadRequest)) status = HttpStatus.BAD_REQUEST;
            
        return new ResponseEntity<>(new ApiResponse(e.getMessage()), status);
    }

}