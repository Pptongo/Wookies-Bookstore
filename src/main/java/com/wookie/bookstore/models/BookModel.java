package com.wookie.bookstore.models;

import java.util.Date;

import com.wookie.bookstore.persistance.entities.BookEntity;

/**
 * Public Book model used for the responses.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class BookModel {
    
    /**
     * The ID of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private long id;

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
     * The Cover Image url of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String coverImage;

    /**
     * The public Price of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private Double price;

    /**
     * The Published date of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private Date publishDate;

    /**
     * The @see @{link AuthorModel} associated with the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private AuthorModel author;

    /**
     * Create an empty book model.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    public BookModel() { }

    /**
     * Create a book model using the properties from the given @see {@link BookEntity}.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param book The @see {@link BookEntity} with the properties to be mapped.
     */
    public BookModel(BookEntity book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.coverImage = book.getCoverImage();
        this.price = book.getPrice();
        this.publishDate = book.getPublishDate();
        this.author = new AuthorModel(book.getAuthor());
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCoverImage() { return coverImage; }

    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public Date getPublishDate() { return publishDate; }

    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public AuthorModel getAuthor() { return author; }

    public void setAuthor(AuthorModel author) { this.author = author; }

}
