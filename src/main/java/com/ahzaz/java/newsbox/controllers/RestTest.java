package com.ahzaz.java.newsbox.controllers;

import com.ahzaz.java.newsbox.model.News;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ahzaz
 */

@Controller
public class RestTest {

    public News news(Integer id) {
        News news = new News();
        news.setHeadline("Headline");
        news.setDetails("This is a news with id " + id);
        return news;
    }

    @RequestMapping(path = "/json/api/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public News newsJSON(@PathVariable("id") Integer id) {
        return news(id);
    }

    @RequestMapping(path = "/xml/api/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public News newsXML(@PathVariable("id") Integer id) {
        return news(id);
    }
}
