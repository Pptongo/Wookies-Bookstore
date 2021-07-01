package com.wookie.bookstore.requests;

/**
 * Represents the properties required to publish a book in a web service.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class PublishBookRequest {

    /**
     * The Title of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String title;

    /**
     * The Description of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String description;

    /**
     * The URL of the Cover Image of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String coverImage;

    /**
     * The Price of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private Double price;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCoverImage() { return coverImage; }

    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }
    
}
