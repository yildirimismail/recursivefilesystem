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

    private Class<T> type;

    public HibernateRepositorySupport() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    public void bindSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    protected Session getCurrentSession() {
        return getHibernateTemplate().getSessionFactory().getCurrentSession();
    }

    public ID persist(T newInstance) {
        return (ID) getHibernateTemplate().save(newInstance);
    }

    public void update(T transientObject) {
        getHibernateTemplate().update(transientObject);
    }

    public void delete(T persistentObject) {
        getHibernateTemplate().delete(persistentObject);
    }

    public T get(ID id) {
        return (T) getHibernateTemplate().get(type, id);
    }

    public T load(ID id) {
        return (T) getHibernateTemplate().load(type, id);
    }

    public List<T> findAll() {
        return (List<T>) getHibernateTemplate().find("from " + type.getName());
    }

    public void flush() {
        getHibernateTemplate().flush();
    }

    public void clear() {
        getHibernateTemplate().clear();
    }

    public ID getIdentifier(T transientObject) {
        return (ID) getCurrentSession().getIdentifier(transientObject);
    }
}