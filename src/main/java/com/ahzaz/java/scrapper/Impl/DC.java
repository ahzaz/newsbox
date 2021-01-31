package com.ahzaz.java.scrapper.Impl;

import com.ahzaz.java.scrapper.Website;
import com.ahzaz.java.newsbox.utils.StringUtils;
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
public class DC extends Website {

    private String url = "http://www.deccanchronicle.com/";

    @Override
    public Map<String, String> getNewsMap() throws IOException {
        Map<String, String> linksMap = new HashMap<>();
        Document page = Jsoup.connect(url).get();
        Elements links = page.select(".tstry-feed-sml-a a");
        Elements linkText = page.select(".tstry-feed-sml-a h3");
        for (int i = 0; i < links.size(); i++) {
            Element link = links.get(i);
            Element text = linkText.get(i);
            String href = link.absUrl("href");
            String title = text.text();
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
    public String getDetails(Document doc) {
        String text = doc.select("#storyBody").get(0).text();
        if (text.indexOf(":") < 15) {
            text = text.substring(text.indexOf(":") + 1);
        }
        return StringUtils.truncateWithDots(text, 300);
    }
}
