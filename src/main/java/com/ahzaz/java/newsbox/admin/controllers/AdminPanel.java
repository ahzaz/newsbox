package com.ahzaz.java.newsbox.admin.controllers;

import com.ahzaz.java.newsbox.security.AdminSecure;
import com.ahzaz.java.newsbox.services.NewsService;
import com.ahzaz.java.newsbox.services.TagService;
import com.ahzaz.java.newsbox.utils.SessionUtils;
import com.ahzaz.java.newsbox.model.News;
import com.ahzaz.java.newsbox.model.News.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Ahzaz
 */
@Controller
public class AdminPanel implements AdminSecure {

    @Autowired
    TagService tagService;

    @Autowired
    NewsService newsService;

    @RequestMapping("/su/panel")
    public String adminPanel() {
        return "admin/panel";
    }

    @RequestMapping(value = "/rest/tags", produces = "application/json")
    @ResponseBody
    public List<String> getTags() {
        return tagService.getTags();
    }

    @RequestMapping(value = "/su/save", method = RequestMethod.POST)
    @ResponseBody
    public void saveNews(@ModelAttribute News news) {
        newsService.saveOrUpdate(news);
    }

    @RequestMapping(value = "/su/edit/{newsId}")
    public String editNews(@PathVariable("newsId") Integer newsId, ModelMap modelMap) {
        modelMap.addAttribute("news", newsService.findById(newsId));
        return "admin/panel";
    }

    @RequestMapping(value = "/su/delete/{newsId}")
    @ResponseBody
    public void deleteNews(@PathVariable("newsId") Integer newsId) {
        newsService.delete(newsId);
    }

    @RequestMapping(value = "/su/view/{status}")
    public String view(@PathVariable("status") String status, ModelMap modelMap) {
        List<News> newsList = newsService.findByStatus(Status.valueOf(status.toUpperCase()));
        modelMap.addAttribute("adminLoggedIn", SessionUtils.isAdminLoggedIn());
        modelMap.addAttribute("newsList", newsList);
        modelMap.addAttribute("newsType", News.NewsType.NEWS);
        modelMap.addAttribute("context", "news");
        return "home/home";
    }


}
