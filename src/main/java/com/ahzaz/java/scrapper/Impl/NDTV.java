package com.ahzaz.java.scrapper.Impl;

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
public class NDTV extends Website {

    private final String url = "http://www.ndtv.com/";
    private final String latesNews = "http://www.ndtv.com/latest?pfrom=home-topnavigation";
    private final String indiaNews = "http://www.ndtv.com/india?pfrom=home-topnavigation";

    @Override
    public Map<String, String> getNewsMap() throws IOException {
        Map<String, String> linksMap = new HashMap<>();
        Document page = Jsoup.connect(url).get();
        Elements links = page.select("h1 .item-title");
        links.addAll(page.select("h2 .item-title"));

        for (Element link : links) {
            String title = link.text();
            String href = link.absUrl("href");
            if (title.isEmpty()) {
                title = href;
            }
            if (!title.isEmpty()) {
                linksMap.put(title, href);
            }
        }


        page = Jsoup.connect(latesNews).get();
        links = page.select(".nstory_header a");

        page = Jsoup.connect(indiaNews).get();
        links.addAll(page.select(".nstory_header a"));

        for (Element link : links) {
            String title = link.attr("title");
            String href = link.absUrl("href");
            if (title.isEmpty()) {
                title = href;
            }
            if (!title.isEmpty()) {
                linksMap.put(title, href);
            }
        }

        return linksMap;
    }
}
