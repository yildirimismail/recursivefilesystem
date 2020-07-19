package com.fsystem.hibernate.helper;

import java.io.Serializable;
import java.util.List;

public interface HibernateRepository<T, ID extends Serializable> {

    ID persist(T newInstance);
    void update(T transientObject);
    void delete(T persistentObject);
    T get(ID id);
    T load(ID id);
    List<T> findAll();
    void flush();
    void clear();
    ID getIdentifier(T transientObject);
}