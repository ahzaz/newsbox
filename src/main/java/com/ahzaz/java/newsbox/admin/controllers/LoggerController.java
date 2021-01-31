package com.ahzaz.java.newsbox.admin.controllers;

import com.ahzaz.java.newsbox.security.AdminSecure;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Ahzaz
 */
@Controller
public class LoggerController implements AdminSecure {

    private static final String LOG_FILE = "newsbox.log".trim();
    private static final String LOG_DIR = "/home/prod/logs/".trim();

    @RequestMapping("/su/logs")
    public String currentLog(ModelMap modelMap) {

        String logFile = LOG_DIR + LOG_FILE;
        modelMap.addAttribute("logContent", getLogContents(logFile));
        return "admin/logs";
    }

    @RequestMapping("/su/logs/{date}")
    public String currentLog(@PathVariable("date") String date, ModelMap modelMap) {
        String logFile = LOG_DIR + LOG_FILE + "." + date;
        modelMap.addAttribute("logContent", getLogContents(logFile));
        return "admin/logs";
    }

    private String getLogContents(String logFile) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(logFile);
        } catch (FileNotFoundException e) {
            return "File Not found '" + logFile + "'";
        }
        Scanner scanner = new Scanner(fis);
        StringBuilder log = new StringBuilder();
        while (scanner.hasNext()) {
            log.append(scanner.nextLine());
        }
        scanner.close();
        try {
            fis.close();
        } catch (IOException e) {
            return e.getMessage();
        }
        return log.toString().replaceAll("\n", "<br/>");
    }


}
