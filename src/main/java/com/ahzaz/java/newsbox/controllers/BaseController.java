package com.ahzaz.java.newsbox.controllers;

import com.ahzaz.java.newsbox.model.News;
import com.ahzaz.java.newsbox.model.News.NewsType;
import com.ahzaz.java.newsbox.services.BaseService;
import com.ahzaz.java.newsbox.utils.SessionUtils;
import org.springframework.ui.ModelMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ahzaz
 */

public abstract class BaseController {

    public static int COOKIE_HOURS = 1 * 60 * 60;
    public static int RESULTS_PER_PAGE = 20;
    public static int DEFAULT_TRENDING_NEWS_COUNT = 10;

//    @Autowired
//    NewsService newsService;

    protected abstract NewsType getType();

    protected abstract BaseService getService();

    protected abstract String getContext();

    public String newsFeed(ModelMap modelMap) {
        List<News> newsList = getService().getForPage(0);
        List<News> headlines = getService().getTrending();
        modelMap.addAttribute("newsList", newsList);
        modelMap.addAttribute("trending", headlines);
        modelMap.addAttribute("adminLoggedIn", SessionUtils.isAdminLoggedIn());
        modelMap.addAttribute("hasNext", newsList.size() == RESULTS_PER_PAGE);
        modelMap.addAttribute("hasPrevious", false);
        modelMap.addAttribute("currentPage", 1);
        modelMap.addAttribute("newsType", getType());
        modelMap.addAttribute("context", getContext());
        return "home/home";  // "news/newsfeed";
    }

    public String news(Integer newsId, String newsTitle, ModelMap modelMap, HttpServletResponse response, HttpServletRequest request) {
        News news = getService().findById(newsId);
        Cookie pageViewCookie = null;
        String cookieName = "V" + getContext() + newsId;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            int totalCookies = request.getCookies().length;
            for (int i = 0; i < totalCookies; ++i) {
                if (cookies[i].getName().equals(cookieName)) {
                    pageViewCookie = cookies[i];
                    break;
                }
            }
        }
        if (pageViewCookie == null) {
            pageViewCookie = new Cookie(cookieName, "1");
            pageViewCookie.setMaxAge(COOKIE_HOURS);
            response.addCookie(pageViewCookie);
            news.setVisits(news.getVisits() + 1);
            getService().update(news);
        }

        modelMap.addAttribute("news", news);
        modelMap.addAttribute("trending", getService().getTrending());
        modelMap.addAttribute("urlRewrite", !newsTitle.equals(news.getTitle()));
        modelMap.addAttribute("newsType", getType());
        modelMap.addAttribute("context", getContext());

        //Redirect may be?
        return "news/news";
    }

    public String newsForPage(Integer page, ModelMap modelMap) {
        if (page < 2)
            return "redirect:/";

        List<News> newsList = getService().getForPage(page - 1);
        if (newsList.isEmpty())
            return "redirect:/";

        List<News> headlines = getService().getTrending();
        modelMap.addAttribute("newsList", newsList);
        modelMap.addAttribute("trending", headlines);
        modelMap.addAttribute("adminLoggedIn", SessionUtils.isAdminLoggedIn());
        modelMap.addAttribute("hasNext", newsList.size() == RESULTS_PER_PAGE);
        modelMap.addAttribute("hasPrevious", page > 0);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("newsType", getType());
        modelMap.addAttribute("context", getContext());


        return "home/home";
    }
}
