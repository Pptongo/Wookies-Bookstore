package com.wookie.bookstore.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.wookie.bookstore.models.BookModel;
import com.wookie.bookstore.persistance.entities.BookEntity;
import com.wookie.bookstore.persistance.entities.UserEntity;
import com.wookie.bookstore.persistance.repository.BookRepository;
import com.wookie.bookstore.persistance.repository.UserRepository;
import com.wookie.bookstore.requests.PublishBookRequest;
import com.wookie.bookstore.service.BookService;
import com.wookie.bookstore.shared.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BookEntity> getAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public BookModel publish(PublishBookRequest request) throws Exception {
        BookModel book = null;

        if (request != null) {
            UserEntity user = getAuthenticatedUser();

            if (user != null) {
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

    @Override
    public BookModel update(long id, PublishBookRequest request) throws Exception {
        BookModel book = null;

        if (request != null) {
            UserEntity user = getAuthenticatedUser();

            if (user != null) {
                BookEntity savedBook = bookRepository.findById(id).orElse(null);

                if (savedBook != null) {
                    savedBook.setTitle(request.getTitle());
                    savedBook.setDescription(request.getDescription());
                    savedBook.setCoverImage(request.getCoverImage());
                    savedBook.setPrice(request.getPrice());
                    
                    bookRepository.save(savedBook);
                    
                    book = new BookModel(savedBook);
                } else {
                    throw new Exception("The book doesn't exists");
                }
            } else {
                throw new Exception(Constants.Unauthorized);
            }
        } else {
            throw new Exception(Constants.BadRequest);
        }
        
        return book;
    }

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
