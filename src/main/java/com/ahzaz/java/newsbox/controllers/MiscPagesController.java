package com.ahzaz.java.newsbox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ahzaz
 */
@Controller
public class MiscPagesController {
    @RequestMapping("/privacy")
    public String privacy() {
        return "/privacy";
    }
}
