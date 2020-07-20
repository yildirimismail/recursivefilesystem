package com.fsystem.hibernate.helper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
@Transactional
public abstract class HibernateRepositorySupport<T, ID extends Serializable> extends HibernateDaoSupport implements HibernateRepository<T, ID> {

    @Autowired
    public void bindSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    protected Session getCurrentSession() {
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

}