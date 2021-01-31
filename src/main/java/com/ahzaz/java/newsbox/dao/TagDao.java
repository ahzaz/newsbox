package com.ahzaz.java.newsbox.dao;

import com.ahzaz.java.newsbox.model.Tag;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ahzaz
 */
@Repository
public class TagDao extends GenericDao<Tag> {
    public TagDao() {
        super(Tag.class);
    }

    @Transactional
    public Tag findByTagName(String name) {
        Tag tag = findOneByKey("name", name);
        if(tag != null)
            Hibernate.initialize(tag.getNewsSet());
        return tag;
    }


    public List<Tag> getAllTags() {
        return getCriteria().list();
    }
}
