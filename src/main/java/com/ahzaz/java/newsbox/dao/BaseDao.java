package com.ahzaz.java.newsbox.dao;

import com.ahzaz.java.newsbox.model.News;
import com.ahzaz.java.newsbox.model.News.Status;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Ahzaz
 */
public class BaseDao extends GenericDao<News> {

    public BaseDao() {
        super(News.class);
    }

    public List<News> getTopNBy(int n, String column) {
        return getCriteria()
                .add(Restrictions.eq("status", Status.PUBLISHED))
                .addOrder(Order.desc(column))
                .setFetchMode("tags", FetchMode.SELECT)
                .setMaxResults(n)
                .list();
    }

    public List<News> getLatestNewsByDate(Date time) {
        return getCriteria()
                .add(Restrictions.eq("status", Status.PUBLISHED))
                .add(Restrictions.ge("publishDate", time))
                .setCacheable(true)
                .list();
    }

    @Transactional
    public void delete(Integer newsId) {
        Session session = sessionFactory.getCurrentSession();
        News news = (News) session.load(News.class, newsId);
//        session.delete(news);
        news.setStatus(Status.DELETED);
        session.update(news);
        session.flush();
    }

    public List<News> getForPage(Integer page, Integer resultsPerPage, String orderBy) {
        return getCriteria()
                .add(Restrictions.eq("status", Status.PUBLISHED))
                .addOrder(Order.desc(orderBy))
                .addOrder(Order.desc("newsId"))
                .setMaxResults(resultsPerPage)
                .setFetchMode("tags", FetchMode.SELECT)
                .setFirstResult(page * resultsPerPage)
                .list();
    }

}