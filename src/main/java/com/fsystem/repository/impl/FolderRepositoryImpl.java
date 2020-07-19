package com.fsystem.repository.impl;

import com.fsystem.entity.File;
import com.fsystem.entity.Folder;
import com.fsystem.hibernate.helper.HibernateRepositorySupport;
import com.fsystem.repository.FileRepository;
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
                .createAlias("folder","f")
                .add(Restrictions.eq("f.id",folderId)).list();
    }
}