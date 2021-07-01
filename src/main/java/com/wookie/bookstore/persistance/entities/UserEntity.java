package com.wookie.bookstore.persistance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents the User object mapped with the database.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "USER_AUTHORS")
public class UserEntity {

    /**
     * The ID of the user.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    /**
     * The Username used to authenticate the user.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * The First Name of the author.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "FIRST_NAME")
    private String firstName;

    /**
     * The Last Name of the author.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "LAST_NAME")
    private String lastName;

    /**
     * The Password of the user.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "PASSWORD", columnDefinition = "CHAR")
    private String password;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassword() { return password != null ? password.trim() : null; }

    public void setPassword(String password) { this.password = password; }

    /**
     * Return true if the user is Darth Vader to prevent any action from this user.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @return A boolean to indicate if the user is or not Darth Vader.
     */
    public Boolean isDarthVader() {
        Boolean isDarthVader = false;

        if (username != null) {
            String usu = username.toLowerCase();

            if (usu.equals("darth") || usu.equals("vader") || usu.equals("darthvader")) isDarthVader = true;
        }

        return isDarthVader;
    }

}
