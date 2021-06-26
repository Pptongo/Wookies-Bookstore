package com.wookie.bookstore.models;

import com.wookie.bookstore.persistance.entities.UserEntity;

public class AuthorModel {
    
    private long id;

    private String fullename;

    public AuthorModel(UserEntity user) {
        this.id = user.getId();
        this.fullename = String.format("%s %s", user.getFirstName(), user.getLastName());
    }

    public long getId() {
        return id;
    }

    public String getFullname() {
        return fullename;
    }

}
