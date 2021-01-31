package com.ahzaz.java.newsbox.services;

import com.ahzaz.java.newsbox.dao.BaseDao;
import com.ahzaz.java.newsbox.dao.NewsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ahzaz
 */
@Service
public class NewsService extends BaseService {


    @Autowired
    private NewsDao newsDao;

    private final Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Override
    public BaseDao getDao() {
        return newsDao;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    public NewsService() {
    }

}
