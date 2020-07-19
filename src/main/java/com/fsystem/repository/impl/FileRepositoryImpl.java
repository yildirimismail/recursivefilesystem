package com.fsystem.repository.impl;

import com.fsystem.entity.File;
import com.fsystem.hibernate.helper.HibernateRepositorySupport;
import com.fsystem.repository.FileRepository;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileRepositoryImpl extends HibernateRepositorySupport<File, Integer> implements FileRepository {
    @Override
    @SuppressWarnings(value = "unchecked")
    public List<File> findFolderByFolderId(Integer folderId) {
        return getCurrentSession().createCriteria(File.class)
                .createAlias("folder","f")
                .add(Restrictions.eq("f.id",folderId)).list();
    }
}