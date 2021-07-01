package com.wookie.bookstore.shared;

/**
 * Contains all the global messages tha can be used for any response.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class Messages {
    
    /**
     * Private constructor that doesn't allow to initialize this object.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private Messages() { }

    public static final String BookNotExists = "The book doesn't exists";
    public static final String UnauthorizedAccessToTheBook = "You cannot manipulate this book, since you are not the author!";
    public static final String DarthVader = "Darth Vader is not authorized to publish any book here!";

}
