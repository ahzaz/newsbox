package com.ahzaz.java.newsbox.services;

import com.ahzaz.java.newsbox.model.News;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahzaz
 */
@Service
public class FacebookPostService {


    private static final Logger logger = LoggerFactory.getLogger(FacebookPostService.class);

    public void postToPage(News news) {
        logger.info("Posting on facebook : " + news.getHeadline());

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://newsbyte.info/php/postToPage.php?qweefxa=6sd6a8s");
        List<NameValuePair> nameValuePairs = new ArrayList<>(3);
        nameValuePairs.add(new BasicNameValuePair("message", news.getHeadline()));
        nameValuePairs.add(new BasicNameValuePair("link", news.getAbsUrl()));
        nameValuePairs.add(new BasicNameValuePair("qasdf12d3", "dtwvfbxyt783t478btx7t7b182t1"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            CloseableHttpResponse response = httpClient.execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
