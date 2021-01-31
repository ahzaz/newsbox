package com.ahzaz.java.newsbox.dao;

import com.ahzaz.java.newsbox.model.News;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author Ahzaz
 */
@Repository
public class VideoDao extends BaseDao {
    @Override
    protected Criteria getCriteria() {
        return super.getCriteria()
                .add(Restrictions.eq("type", News.NewsType.VIDEOS))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                ;
    }
}
