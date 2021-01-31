package com.ahzaz.java.newsbox.model;

import com.ahzaz.java.newsbox.utils.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

/**
 * @author Ahzaz
 */
@Entity
@Table(name = "news", indexes = {@Index(columnList = "publish_date", name = "publish_date_hindex")})
public class News {

    public enum Status {
        DRAFT("Draft"),
        DELETED("Deleted"),
        PUBLISHED("Published");

        String desc;

        Status(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum NewsType {
        NEWS("news"),
        VIDEOS("videos"),
        IMAGES("images"),
        BLOG("blog");

        String desc;

        NewsType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

    }


    @Transient
    private final String defaultImage = "/static/media/images/favicon.png";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer newsId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "headline", nullable = false)
    private String headline;

    @Column(name = "details", nullable = false, length = 2000)
    private String details;

    @Column(name = "source", nullable = false, length = 500)
    private String source;

    @Column(name = "imageUrl", length = 500)
    private String imageUrl;

    @Column(name = "publish_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date publishDate;

    @Column(name = "visits", nullable = false)
    private Long visits = 0L;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.DRAFT;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "news_to_tags",
            joinColumns = {@JoinColumn(name = "newsId", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tagId", nullable = false, updatable = false)})
    @Column(name = "tags")
    private Set<Tag> tags;


    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private NewsType type;


    public News() {
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
//        this.title = title;

    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline.trim();
        this.title = this.headline.
                toLowerCase()
                .replaceAll("[^a-zA-Z0-9 ]", "")
                .replace(' ', '-');
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        if (source.matches("^https?://.*"))
            this.source = source;
        else
            this.source = "http://" + source;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Long getVisits() {
        return visits + (getHeadline().hashCode() % 25) + 25;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public String getUrl() {
        return "/" + type.desc + "/" + newsId + "/" + title;
    }

    public String getAbsUrl() {
        return "http://newsbyte.info/news/" + newsId + "/" + title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public NewsType getType() {
        return type;
    }

    public void setType(NewsType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "News{" +
                "defaultImage='" + defaultImage + '\'' +
                ", newsId=" + newsId +
                ", title='" + title + '\'' +
                ", headline='" + headline + '\'' +
                ", details='" + details + '\'' +
                ", source='" + source + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishDate=" + publishDate +
                ", visits=" + visits +
                ", tags=" + tags +
                ", type=" + type.desc +
                '}';
    }

    public News(String title, String headline, String details, String source, Date publishDate, Set<Tag> tags) {
        this.title = title;
        this.headline = headline;
        this.details = details;
        this.source = source;
        this.publishDate = publishDate;
        this.tags = tags;
        this.type = NewsType.NEWS;
    }

    public static class NewsComparator implements Comparator<News> {

        @Override
        public int compare(News o1, News o2) {
            return o2.publishDate.compareTo(o1.publishDate);
        }
    }

    public String trendingHeadline() {
        return StringUtils.truncateWithDots(headline, 30);
    }

    public String shortenHeadline() {
        int maxLength = 80;
        return StringUtils.truncateWithDots(headline, maxLength);
    }


    public Long getScore() {
        return visits;
    }

    public static class ScoreComparator implements Comparator<News> {
        @Override
        public int compare(News o1, News o2) {
            return o2.getScore().compareTo(o1.getScore());
        }
    }
}
