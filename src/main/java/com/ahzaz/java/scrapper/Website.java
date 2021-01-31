package com.ahzaz.java.scrapper;

import com.ahzaz.java.newsbox.model.News;
import com.ahzaz.java.newsbox.model.Tag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Ahzaz
 */
public abstract class Website {

    private final Logger logger = LoggerFactory.getLogger(Website.class);

    /**
     * @return A map from headline to news link
     * @throws IOException
     */
    public abstract Map<String, String> getNewsMap() throws IOException;

    public String getHeadline(Document doc) {
        return doc.select("[property=\"og:title\"]").get(0).attr("content");
    }

    public String getImageUrl(Document doc) {
        return doc.select("[property=\"og:image\"]").get(0).attr("content");
    }

    public String getSource(Document doc) {
        return doc.select("[property=\"og:url\"]").get(0).attr("content");
    }

    public String getDetails(Document doc) {
        return doc.select("[property=\"og:description\"]").get(0).attr("content");
    }

    private Set<Tag> getTags(String[] tags) {
        Set<Tag> tagSet = new HashSet<>(tags.length);
        for (String tag : tags) {
            tagSet.add(new Tag(tag.trim()));
        }
        return tagSet;
    }

    public News makeNews(String page, String[] tags, String status) throws IOException {
        Document doc = Jsoup.connect(page).get();
        News news = new News();
        news.setTags(getTags(tags));
        news.setStatus(News.Status.valueOf(status.toUpperCase()));
        news.setVisits(0L);
        String headline = getHeadline(doc);
        news.setHeadline(headline);
        news.setImageUrl(getImageUrl(doc));
        news.setSource(getSource(doc));
        news.setDetails(getDetails(doc));

        return news;
    }

}
