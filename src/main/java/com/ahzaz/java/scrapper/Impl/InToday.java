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
public class InToday extends Website {

    private String url = "http://indiatoday.intoday.in/";

    @Override
    public Map<String, String> getNewsMap() throws IOException {
        Map<String, String> linksMap = new HashMap<>();
        Document page = Jsoup.connect(url).get();
        Elements links = page.select(".newstextlink a");
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
        return linksMap;
    }

    @Override
    public String getDetails(Document doc) {
        String text = doc.select("[itemprop=\"articleBody\"]").get(0).text();
        return StringUtils.truncateWithDots(text, 300);
    }

}
