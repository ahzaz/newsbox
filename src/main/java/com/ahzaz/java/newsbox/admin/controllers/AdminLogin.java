package com.ahzaz.java.newsbox.admin.controllers;

import com.ahzaz.java.newsbox.model.AdminUser;
import com.ahzaz.java.newsbox.services.auth.SecurityService;
import com.ahzaz.java.newsbox.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ahzaz
 */
@Controller
public class AdminLogin {

    @Autowired
    SecurityService securityService;

    private static final Logger logger = LoggerFactory.getLogger(AdminLogin.class);

    @RequestMapping(value = "/su/login", method = RequestMethod.GET)
    public String adminLoginPage() {
        logger.info("Admin logged " + SessionUtils.isAdminLoggedIn());
        if (SessionUtils.isAdminLoggedIn())
            return "redirect:/su/panel";
        return "admin/login";
    }

    @RequestMapping(value = "/su/login", method = RequestMethod.POST)
    public String login(@ModelAttribute AdminUser userForm, HttpServletRequest request) {
        logger.info("Admin login from " + userForm.getUsername() + " " + userForm.getPassword());
        securityService.login(userForm.getUsername(), userForm.getPassword());
        return "redirect:/su/login";
    }

    @RequestMapping(value = "/su/logout")
    public String logout() {
        securityService.logout();
        return "redirect:/";
    }


}
