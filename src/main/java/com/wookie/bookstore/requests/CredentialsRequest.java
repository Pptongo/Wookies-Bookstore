package com.wookie.bookstore.requests;

/**
 * Represents the Credentials properties request.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class CredentialsRequest {

    /**
     * The username for authentication.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String username;

    /**
     * The password for authentication.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String password;

    /**
     * Create empty credentials request object.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    public CredentialsRequest() { }

    /**
     * Create a credentials request object with given password and username passed as parameters.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param username The username used to authenticate.
     * @param password The password of the given username.
     */
    public CredentialsRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
    
}
