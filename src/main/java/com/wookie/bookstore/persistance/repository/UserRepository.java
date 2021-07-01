package com.wookie.bookstore.persistance.repository;

import com.wookie.bookstore.persistance.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * All the database operations for @see {@link UserEntity} are defined here.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    /**
     * Find @see {@link UserEntity} using the username property.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param username The username used to find the user.
     * @return The @see {@link UserEntity} found.
     */
    public UserEntity findByUsername(String username);
    
}
