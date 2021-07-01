package com.wookie.bookstore.persistance.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wookie.bookstore.requests.PublishBookRequest;

/**
 * Represents the Book model mapped with the database.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "BOOKS")
public class BookEntity {
    
    /**
     * The primary key of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    /**
     * The Title of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * The Description of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * The @see {@link UserEntity} that contains the author information.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @JoinColumn(name = "AUTHOR")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserEntity author;

    /**
     * The url of the cover image of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "COVER_IMAGE")
    private String coverImage;

    /**
     * The price of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "PRICE", columnDefinition = "Decimal(10,2)")
    private Double price;

    /**
     * The published date of the book.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    @Column(name = "PUBLISH_DATE")
    private Date publishDate;

    /**
     * Create an empty book entity.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    public BookEntity() { }

    /**
     * Create a book entity with the given parameters.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param request The @see {@link PublishBookRequest} to get the properties to be mapped.
     * @param author The @see {@link UserEntity} to define the author of the book.
     */
    public BookEntity(PublishBookRequest request, UserEntity author) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.coverImage = request.getCoverImage();
        this.price = request.getPrice();
        this.publishDate = new Date();
        this.author = author;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public UserEntity getAuthor() { return author; }

    public void setAuthor(UserEntity author) { this.author = author; }

    public String getCoverImage() { return coverImage; }

    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public Date getPublishDate() { return publishDate; }

    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

}
