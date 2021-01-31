package com.ahzaz.java.newsbox.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;

/**
 * @author Ahzaz
 */
@Entity
@Table(name = "blog_post")
public class BlogPost {

    @Id
    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private SortedSet<BlogPages> pages;

    private Set<Tag> tags;

    private Date publishDate;

    public BlogPost() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SortedSet<BlogPages> getPages() {
        return pages;
    }

    public void setPages(SortedSet<BlogPages> pages) {
        this.pages = pages;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}

