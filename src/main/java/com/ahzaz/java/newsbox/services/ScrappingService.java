package com.ahzaz.java.newsbox.services;

import com.ahzaz.java.newsbox.exceptions.NewsAlreadyExistsException;
import com.ahzaz.java.scrapper.Website;
import com.ahzaz.java.newsbox.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ahzaz
 */
@Service
public class ScrappingService {

    private final Logger logger = LoggerFactory.getLogger(ScrappingService.class);

    @Autowired
    NewsService newsService;

    @Autowired
    FacebookPostService facebookPostService;


    public void scrap(List<String> pages, List<String> tags, String status, String className) {
        Website site = getSite(className);
        if (site == null) {
            logger.error("Can not get site");
            return;
        }
        News news;
        Set<String> headLines = new HashSet<>(20);
        for (News publishedNews : newsService.getLatestNews(20))
            headLines.add(publishedNews.getHeadline().toLowerCase());

        for (int i = 0; i < pages.size(); i++) {
            try {
                news = site.makeNews(pages.get(i), tags.get(i).split(","), status);
                if (headLines.contains(news.getHeadline().toLowerCase()))
                    throw new NewsAlreadyExistsException(news.getHeadline());
                newsService.saveOrUpdate(news);
                if (news.getStatus() == News.Status.PUBLISHED) {
                    facebookPostService.postToPage(news);
                }
            } catch (IOException e) {
                logger.error("Can not add news : " + pages.get(i));
                logger.error(e.getMessage());
                e.printStackTrace();
            } catch (NewsAlreadyExistsException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public Website getSite(String className) {
        try {
            return (Website) Class.forName(className).newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, String> getNewsList(String className) {
        Website site = getSite(className);
        Map<String, String> map;
        try {
            map = site.getNewsMap();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
        List<News> publishedNews = newsService.getLatestNews(30);
        for (News news : publishedNews) {
            map.remove(news.getHeadline());
        }
        return map;
    }
}
