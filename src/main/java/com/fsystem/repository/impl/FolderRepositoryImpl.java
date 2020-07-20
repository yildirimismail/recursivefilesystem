package com.fsystem.repository.impl;

import com.fsystem.domain.entity.Folder;
import com.fsystem.hibernate.helper.HibernateRepositorySupport;
import com.fsystem.repository.FolderRepository;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FolderRepositoryImpl extends HibernateRepositorySupport<Folder, Integer> implements FolderRepository {
    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Folder> findFoldersByFolderId(Integer folderId) {
        return getCurrentSession().createCriteria(Folder.class)
                .add(Restrictions.eq("parentFolder.id",folderId)).list();
    }

    @Override
    public Folder findRootFolder() {
        return (Folder) getCurrentSession().createCriteria(Folder.class)
                .add(Restrictions.isNull("parentFolder")).uniqueResult();
    }
}