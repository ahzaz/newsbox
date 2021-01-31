package com.ahzaz.java.newsbox.admin.controllers;

import com.ahzaz.java.newsbox.services.NewsService;
import com.ahzaz.java.newsbox.services.TagService;
import com.ahzaz.java.newsbox.model.News;
import com.ahzaz.java.newsbox.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahzaz
 */
@Transactional
@Controller
public class SuperAdmin {

    @Autowired
    NewsService newsService;

    @Autowired
    TagService tagService;

    @RequestMapping("/su/addToDB")
    public String addToDB(){
        Tag t1 = tagService.findByTagName("tag1");
        Tag t2 = tagService.findByTagName("tag2");
        Tag t3 = new Tag(); t3.setName("tag3");
        Tag t4 = tagService.findByTagName("tag4");
        Tag t5 = tagService.findByTagName("tag5");

        Set<Tag> tagSet1 = new HashSet<>();
        Set<Tag> tagSet2 = new HashSet<>();

        tagSet1.add(t1);
        tagSet1.add(t2);
        tagSet1.add(t3);
        tagSet2.add(t4);
        tagSet2.add(t5);
        tagSet2.add(t3);
        News n1 = new News("title-3", "headling 3", "details 3", "source-3", new Date(), tagSet1);
        News n2 = new News("title-4", "headling 4", "details 4", "source-4", new Date(), tagSet2);

        newsService.saveOrUpdate(n1);
        newsService.saveOrUpdate(n2);
        return "home/home";
    }

}
