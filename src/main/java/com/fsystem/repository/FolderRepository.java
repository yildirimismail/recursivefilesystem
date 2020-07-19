package com.fsystem.repository;

import com.fsystem.entity.File;
import com.fsystem.entity.Folder;
import com.fsystem.hibernate.helper.HibernateRepository;

import java.util.List;

public interface FolderRepository extends HibernateRepository<Folder, Integer> {
    List<Folder> findFoldersByFolderId(Integer folderId);
}