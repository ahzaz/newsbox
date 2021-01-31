package com.ahzaz.java.newsbox.controllers;

import com.ahzaz.java.newsbox.model.News;
import com.ahzaz.java.newsbox.model.Tag;
import com.ahzaz.java.newsbox.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ahzaz
 */
@Controller
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping("/tags/{tagName}")
    //TODO : Add Videos by tag
    public String newsByTag(@PathVariable("tagName") String tagName, ModelMap modelMap) {
        Tag tag = tagService.findByTagName(tagName);
        modelMap.addAttribute("newsList", tag.getNewsSet());
        modelMap.addAttribute("newsType", News.NewsType.NEWS);
        modelMap.addAttribute("context", "news");
        return "home/home";
    }
}
