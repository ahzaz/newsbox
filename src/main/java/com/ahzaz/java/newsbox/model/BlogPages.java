package com.ahzaz.java.newsbox.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ahzaz
 */
@Entity
@Table(name = "blog_pages")
public class BlogPages implements Comparable<BlogPages> {

    private Long id;

    private Long page;

    private Long blogPostId;

    private String imageUrl;

    private String description;

    public BlogPages() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(Long blogPostId) {
        this.blogPostId = blogPostId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(BlogPages o) {
        return this.page.compareTo(o.getPage());
    }
}
