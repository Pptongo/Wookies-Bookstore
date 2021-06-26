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

@Entity
@Table(name = "BOOKS")
public class BookEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "AUTHOR")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserEntity author;

    @Column(name = "COVER_IMAGE")
    private String coverImage;

    @Column(name = "PRICE", columnDefinition = "Decimal(10,2)")
    private Double price;

    @Column(name = "PUBLISH_DATE")
    private Date publishDate;

    public BookEntity() {

    }

    public BookEntity(PublishBookRequest request, UserEntity author) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.coverImage = request.getCoverImage();
        this.price = request.getPrice();
        this.publishDate = new Date();
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

}
