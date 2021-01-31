package com.ahzaz.java.newsbox.controllers;

import com.ahzaz.java.newsbox.model.News;
import com.ahzaz.java.newsbox.services.NewsService;
import com.ahzaz.java.newsbox.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.ahzaz.java.newsbox.controllers.BaseController.RESULTS_PER_PAGE;

/**
 * @author Ahzaz
 */

@Controller
public class HomeController {

    @Autowired
    NewsService newsService;

    @RequestMapping("/")
    public String home(ModelMap modelMap) {
        List<News> newsList = newsService.getLatestNews();
        List<News> headlines = newsService.getTrending();
        modelMap.addAttribute("newsList", newsList);
        modelMap.addAttribute("trending", headlines);
        modelMap.addAttribute("adminLoggedIn", SessionUtils.isAdminLoggedIn());
        modelMap.addAttribute("hasNext", newsList.size() == RESULTS_PER_PAGE);
        modelMap.addAttribute("hasPrevious", false);
        modelMap.addAttribute("currentPage", 1);
        modelMap.addAttribute("newsType", News.NewsType.NEWS);
        modelMap.addAttribute("context", "news");
        return "home/home";
    }
    /*
            News news = new News();
        news.setTitle("Something-happened-somewhere");
        news.setHeadline("Something has happened somewhere");
        news.setDetails("Something has happened somewhere as reported by the source");
        news.setSource("http://somerandom.newssource.com");
        news.setPublishDate(new Date());
        newsService.saveOrUpdate(news);

     */
}
