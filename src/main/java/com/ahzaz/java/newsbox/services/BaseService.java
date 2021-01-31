package com.ahzaz.java.newsbox.services;

import com.ahzaz.java.newsbox.controllers.BaseController;
import com.ahzaz.java.newsbox.dao.BaseDao;
import com.ahzaz.java.newsbox.dao.TagDao;
import com.ahzaz.java.newsbox.model.News;
import com.ahzaz.java.newsbox.model.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ahzaz
 */
public abstract class BaseService {


//    @Autowired
//    private NewsDao newsDao;

    public abstract BaseDao getDao();

    public abstract Logger getLogger();

    @Autowired
    private TagDao tagDao;

    public BaseService() {
    }

    public List<News> getLatestNews() {
        return getLatestNews(BaseController.RESULTS_PER_PAGE);
    }

    public List<News> getLatestNews(int n) {
        return getDao().getForPage(0, n, "publishDate");
    }

    public void saveOrUpdate(News news) {
        news.setTags(mapTagsToDB(news.getTags()));
        if (news.getStatus() == null)
            news.setStatus(News.Status.PUBLISHED);
        if (news.getType() == null) {
            news.setType(News.NewsType.NEWS);
        }

        if (news.getPublishDate() == null)
            news.setPublishDate(new Date());

        getLogger().info("News to save : " + news.getHeadline());
//        Setting headline, sets the title as well. In case title in not set we re-set the
//        headline to set the title
        if (news.getTitle() != null || news.getTitle().isEmpty())
            news.setHeadline(news.getHeadline());
        getDao().saveOrUpdate(news);
    }

    private Set<Tag> mapTagsToDB(Set<Tag> tags) {
        Set<Tag> tagsFromNews = tags;
        return tagsFromNews.stream().map(tag -> {
            Tag t = tagDao.findByTagName(tag.getName());
            if (t == null) {
                tagDao.save(tag);
                return tagDao.findByTagName(tag.getName());
            }
            return t;
        }).collect(Collectors.toSet());

    }

    public News findById(Integer newsId) {
        return getDao().findById(newsId);
    }

    public List<News> getTrending() {
        Calendar twoDaysBack = Calendar.getInstance();
        twoDaysBack.add(Calendar.DATE, -3);
        List<News> newsList = getDao().getLatestNewsByDate(twoDaysBack.getTime());
        newsList.sort(new News.ScoreComparator());
        return newsList.subList(0, Integer.min(newsList.size(), BaseController.DEFAULT_TRENDING_NEWS_COUNT));
    }

    public void update(News news) {
        getDao().saveOrUpdate(news);
    }

    public void delete(Integer newsId) {
        getDao().delete(newsId);
    }

    public List<News> getForPage(Integer page) {
        return getDao().getForPage(page, BaseController.RESULTS_PER_PAGE, "publishDate");
    }

    public List<News> findByStatus(News.Status status) {
        return getDao().findByKey("status", status);
    }


}
