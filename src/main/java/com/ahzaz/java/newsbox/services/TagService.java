package com.ahzaz.java.newsbox.services;

import com.ahzaz.java.newsbox.dao.TagDao;
import com.ahzaz.java.newsbox.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahzaz
 */
@Service
public class TagService {

    @Autowired
    TagDao tagDao;

    public Tag findByTagName(String name) {
         return tagDao.findByTagName(name);

    }

    public List<String> getTags() {
        return tagDao.getAllTags().stream().map(Tag::getName).collect(Collectors.toList());
    }
}
