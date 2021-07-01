package com.wookie.bookstore.models;

import com.wookie.bookstore.persistance.entities.UserEntity;

/**
 * Represent an Author public model to be used as response.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class AuthorModel {
    
    /**
     * The ID of the author.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private long id;

    /**
     * The full name of the author.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String fullename;

    /**
     * Create an empty author model.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    public AuthorModel() { }

    /**
     * Create an author model from @see {@link UserEntity} properties.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param user The @see {@link UserEntity} used to fill the properties for the author model.
     */
    public AuthorModel(UserEntity user) {
        this.id = user.getId();
        this.fullename = String.format("%s %s", user.getFirstName(), user.getLastName());
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getFullname() { return fullename; }

    public void setFullname(String fullname) { this.fullename = fullname; }

}
