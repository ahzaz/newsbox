package com.ahzaz.java.scrapper.Impl;

import com.ahzaz.java.newsbox.utils.StringUtils;
import com.ahzaz.java.scrapper.Website;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ahzaz
 */
public class HT extends Website {

    private final String worldNewsUrl = "http://www.hindustantimes.com/world-news/";
    private final String indiaNewsUrl = "http://www.hindustantimes.com/india-news/";

    @Override
    public Map<String, String> getNewsMap() throws IOException {
        Map<String, String> linksMap = new HashMap<>();
        linksMap.putAll(getNews(worldNewsUrl));
        linksMap.putAll(getNews(indiaNewsUrl));
        return linksMap;
    }

    private Map<String, String> getNews(String url) throws IOException {
        Document page = Jsoup.connect(url).get();
        Map<String, String> linksMap = new HashMap<>();
        selectAndAdd(page, ".headingfour a", linksMap);
        selectAndAdd(page, ".headlingfive a", linksMap);
        return linksMap;
    }

    private void selectAndAdd(Document page, String selector, Map<String, String> linksMap) {
        Elements links = page.select(".headingfour a");
        for (int i = 0; i < links.size(); i++) {
            Element link = links.get(i);
            String href = link.absUrl("href");
            String title = link.text();
            if (title.isEmpty()) {
                title = href;
            }
            if (!title.isEmpty()) {
                linksMap.put(title, href);
            }
        }
    }

    @Override
    public String getDetails(Document doc) {
        return StringUtils.truncateWithDots(doc.select(".story-details p").text(), 300);
    }
}
