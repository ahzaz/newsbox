package com.ahzaz.java.newsbox.controllers;

import com.ahzaz.java.scrapper.Scrapper;
import com.ahzaz.java.newsbox.security.AdminSecure;
import com.ahzaz.java.newsbox.services.ScrappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Ahzaz
 */
@Controller
public class ScrappingController implements AdminSecure

{

    @Autowired
    ScrappingService scrappingService;

    private final Logger logger = LoggerFactory.getLogger(ScrappingController.class);

    @RequestMapping("/su/scrapper")
    public String scrapper(ModelMap modelMap) {

        modelMap.addAttribute("sites", Scrapper.availableSites);
        return "scrapper/main";
    }

    @RequestMapping(value = "su/scrapper/{site:.+}", method = RequestMethod.GET)
    public String scrapSite(@PathVariable("site") String className, ModelMap modelMap) {
        modelMap.addAttribute("links", scrappingService.getNewsList(className));
        modelMap.addAttribute("website", className);
        return "scrapper/news";
    }

    @RequestMapping(value = "su/scrapper/{site:.+}", method = RequestMethod.POST)
    @ResponseBody
    public void scrapPages(@RequestParam MultiValueMap<String, String> json, @PathVariable("site") String className) {
        List<String> pages = json.get("headlines[]");
        List<String> tags = json.get("tags[]");
        String status = json.get("status").get(0);

        scrappingService.scrap(pages, tags, status, className);
    }
}
