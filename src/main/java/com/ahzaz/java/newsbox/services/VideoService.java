package com.ahzaz.java.newsbox.services;

import com.ahzaz.java.newsbox.dao.BaseDao;
import com.ahzaz.java.newsbox.dao.VideoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ahzaz
 */
@Service
public class VideoService extends BaseService {

    @Autowired
    VideoDao videoDao;

    @Override
    public BaseDao getDao() {
        return videoDao;
    }

    private Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Override
    public Logger getLogger() {
        return logger;
    }
}
