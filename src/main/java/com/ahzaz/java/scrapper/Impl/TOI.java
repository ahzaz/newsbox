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
public class TOI extends Website {


    private String url = "http://timesofindia.indiatimes.com/";

    @Override
    public Map<String, String> getNewsMap() throws IOException {
        Map<String, String> linksMap = new HashMap<>();
        Document page = Jsoup.connect(url).get();
        Elements links = page.select("[data-vr-zone=\"top_stories\"] a");
        links.addAll(page.select("[data-vr-zone=\"latest\"] a"));
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

    @Override
    public String getHeadline(Document doc) {
        return doc
                .select("[property=\"og:title\"]").get(0)
                .attr("content")
                .replaceAll("- Times of India$", "");
    }
}
