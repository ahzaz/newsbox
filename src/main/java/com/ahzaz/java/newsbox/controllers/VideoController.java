package com.ahzaz.java.newsbox.controllers;

import com.ahzaz.java.newsbox.model.News.NewsType;
import com.ahzaz.java.newsbox.services.BaseService;
import com.ahzaz.java.newsbox.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ahzaz
 */
@Controller
public class VideoController extends BaseController {

    @Autowired
    VideoService videoService;

    @Override
    protected NewsType getType() {
        return NewsType.VIDEOS;
    }

    @Override
    protected BaseService getService() {
        return videoService;
    }

    @Override
    protected String getContext() {
        return "videos";
    }


    @RequestMapping("/videos/")
    public String newsFeed(ModelMap modelMap) {
        return super.newsFeed(modelMap);
    }

    @RequestMapping("/videos/{newsId}/{newsTitle}")
    public String news(@PathVariable("newsId") Integer newsId, @PathVariable("newsTitle") String newsTitle, ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        return super.news(newsId, newsTitle, modelMap, response, request);
    }

    @RequestMapping(value = "/videos/{page}")
    public String newsForPage(@PathVariable("page") Integer page, ModelMap modelMap) {
        return super.newsForPage(page, modelMap);
    }
}
