package com.wookie.bookstore.persistance.repository;

import com.wookie.bookstore.persistance.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    public UserEntity findByUsername(String username);
    
}
