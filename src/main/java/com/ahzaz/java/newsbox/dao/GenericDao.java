package com.ahzaz.java.newsbox.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Generic DAO class for other DAO layer to extend.
 *
 * @param <T>
 * @author ronakkhunt
 */
@Transactional
public abstract class GenericDao<T> {

    @Autowired
    SessionFactory sessionFactory;

    Class<T> clazz;

    public GenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public void saveOrUpdate(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @SuppressWarnings("unchecked")
    public T findById(String id) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (T) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public T findById(Integer id) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (T) criteria.uniqueResult();
    }


    @SuppressWarnings("unchecked")
    public T findOneByKey(String key, Object value) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq(key, value));
        return (T) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByKey(String key, Object value) {
        Criteria criteria = getCriteria();
        return criteria.add(Restrictions.eq(key, value))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    protected Criteria getCriteria() {
        return sessionFactory.getCurrentSession().createCriteria(this.clazz);
    }
}
